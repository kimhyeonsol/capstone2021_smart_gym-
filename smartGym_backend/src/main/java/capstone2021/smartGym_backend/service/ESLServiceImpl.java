package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedRead;
import capstone2021.smartGym_backend.domain.ESL;

import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.GymInfo;
import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.repository.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@EnableScheduling
@Transactional
public class ESLServiceImpl implements ESLService {
    private final ReservationRepository reservationRepository;
    private final GymOperationInfoRepository gymOperationInfoRepository;
    private final EquipmentRepository equipmentRepository;
    private final AllowedUserRepository allowedUserRepository;
    private final GymInfoRepository gymInfoRepository;
    private final ESLRepository eslRepository;
    private String oldFile=null;

    FTPClient ftpClient = null; //FTP 관련 변수들
    private static final Logger log = LoggerFactory.getLogger(ESLServiceImpl.class);

    @Autowired
    public ESLServiceImpl(ReservationRepository reservationRepository, GymOperationInfoRepository gymOperationInfoRepository, EquipmentRepository equipmentRepository, AllowedUserRepository allowedUserRepository, GymInfoRepository gymInfoRepository, ESLRepository eslRepository) {
        this.reservationRepository = reservationRepository;
        this.gymOperationInfoRepository = gymOperationInfoRepository;
        this.equipmentRepository = equipmentRepository;
        this.allowedUserRepository = allowedUserRepository;
        this.gymInfoRepository = gymInfoRepository;
        this.eslRepository = eslRepository;
    }

    @Override
    public boolean eslCreate() {
        ESL esl = new ESL();
        esl.setEquipmentID(null);
        esl.setReservationID(null);

        return eslRepository.create(esl);
    }

    @Override
    public boolean eslEquipmentUpdate(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        ESL newEsl = new ESL();
        String csvString=new String();
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";

        try {
            ESL esl = eslRepository.findByID(eslEquipmentMatchingDTO.getEslID());
            newEsl.setEslID(eslEquipmentMatchingDTO.getEslID());
            newEsl.setEquipmentID(eslEquipmentMatchingDTO.getEquipmentID());
            Equipment equipment = equipmentRepository.findByID(eslEquipmentMatchingDTO.getEquipmentID());
            csvString+= makeCsvStringAndEquipmentMatching(equipment, newEsl);//새로 매칭된 운동기구,원래 esl,새로운 esl
            writeCSV(csvString);
            FTPUploader("192.168.1.15", "cgESLUser", "cgESLPassword");
            File fileTest = new File("./src/main/resources/import_" + oldFile + ".csv");
            String fileName = fileTest.getName();
            uploadFile("./src/main/resources/import_" + oldFile + ".csv",fileName,"/Import/");
            disconnect();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean eslEquipmentUnmatch(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {

        return false;
    }

    public String makeCsvStringAndEquipmentMatching(Equipment equipment,ESL newEsl){//매칭된 equipment 객체, 현재 esl객체, 새로 update할 esl 객체

        String csvString=new String();
        List<Reservation> reservationList;
        Reservation reservation;

        if(equipment.getEquipmentAvailable()==0){
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=newEsl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+" "+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }
        else if(equipment.getEquipmentAvailable()==1){
            reservationList=reservationRepository.isInUse(equipment.getEquipmentID());
            newEsl.setReservationID(reservationList.get(0).getReservationID());
            eslRepository.update(newEsl);
            reservation=reservationRepository.findByID(newEsl.getReservationID());
            csvString=csvString+newEsl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+reservation.getUserID().getUserName()+','+reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }
        else if(equipment.getEquipmentAvailable()==2){
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+newEsl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+recentReservation(equipment)+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }

        equipment.setESLID(newEsl.getEslID());
        return csvString;
    }

    @Override
    public boolean eslDelete(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO) {
        ESL findESL = eslRepository.findByID(eslDeleteDetailedReadDTO.getEslID());
        if(equipmentRepository.eslDelete(eslDeleteDetailedReadDTO.getEslID())){
            return eslRepository.delete(findESL);
        }

        return false;
    }

    @Override
    public List<ESL> eslRead() {
        return eslRepository.read();
    }

    @Override
    public ReturnESLDetailedRead eslDetailedRead(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO) {
        ReturnESLDetailedRead returnESLDetailedRead = new ReturnESLDetailedRead();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        // 해당 아이디 esl 객체 찾기
        ESL findESL = eslRepository.findByID(eslDeleteDetailedReadDTO.getEslID());
        if(findESL == null){ //없는 ESL일 경우
            returnESLDetailedRead.setEquipmentAvailable(3);
            return returnESLDetailedRead;
        }
        
        if(findESL.getEquipmentID() == null){ //매칭된 운동기구가 없는 경우
            returnESLDetailedRead.setEquipmentAvailable(4);
            return returnESLDetailedRead;
        }

        //해당 equipment 찾기
        Equipment findEquipment = equipmentRepository.findByID(findESL.getEquipmentID());
        //헬스장 정보
        GymInfo findGymInfo = gymInfoRepository.read();


        //띄워줄 아이디
        returnESLDetailedRead.setEslID(findESL.getEslID());
        //헬스장 이름
        returnESLDetailedRead.setGymInfoName(findGymInfo.getGymInfoName());
        //운동기구 이름
        returnESLDetailedRead.setEquipmentName(findEquipment.getEquipmentName());
        //운동기구 순서
        returnESLDetailedRead.setEquipmentNameNth(findEquipment.getEquipmentNameNth());
        //운동기구 qr코드세팅
        returnESLDetailedRead.setEquipmentQRCode(findEquipment.getEquipmentQRCode());

        if(findEquipment.getEquipmentAvailable() == 0){ //기구 고장
            returnESLDetailedRead.setUserName("");
            returnESLDetailedRead.setStartTime("");
            returnESLDetailedRead.setEndTime("");
            returnESLDetailedRead.setEquipmentAvailable(0);
        }

        // 기존에 구현된
        // available 값이 1초마다 변동되는 메소드를 그냥 이용하여, 단순히 db에 접근해 값을 설정해주기에는
        // 관리자의 api 호출시간을 고려했을 때 값이 처리 되기 전 일 수 있음.
        // 따라서 본 메소드에서 따로 현재 예약 조회 따로 계산
        else { //예약 상태

            List<Reservation> findReservaton = reservationRepository.isInUse(findESL.getEquipmentID());
            //모두 사용 가능한 경우
            if(findReservaton.isEmpty()){
                returnESLDetailedRead.setUserName("");
                if(reservationRepository.recentReservation(findEquipment) == null){ //최근 예약 없는 경우
                    returnESLDetailedRead.setStartTime("");
                }
                else{
                    LocalDateTime startTime = reservationRepository.recentReservation(findEquipment).getStartTime();
                    String startTimeString = format.format(startTime);

                    returnESLDetailedRead.setStartTime(startTimeString);
                }
                returnESLDetailedRead.setEndTime("");
                returnESLDetailedRead.setEquipmentAvailable(2);

            }
            //예약 있음
            else {
                Reservation reservation=findReservaton.get(0);
                returnESLDetailedRead.setUserName(reservation.getUserID().getUserName());

                String startTime = format.format(reservation.getStartTime());
                String endTime = format.format(reservation.getEndTime());

                returnESLDetailedRead.setStartTime(startTime);
                returnESLDetailedRead.setEndTime(endTime);
                returnESLDetailedRead.setEquipmentAvailable(1);
            }
        }

        return returnESLDetailedRead;
    }


    @Override
    @Scheduled(fixedDelay = 1000)//30초마다 체크
    public void eslReservationUpdate() throws Exception {

        List<ESL> eslList = eslRepository.read();

        ESL newEsl = new ESL();

        String csvString=new String();
        String makeCsvString;
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";
        int initStringlength=csvString.length();
        for (ESL esl : eslList) {
            newEsl.setEslID(esl.getEslID());
            newEsl.setEquipmentID(esl.getEquipmentID());
            Equipment equipment=equipmentRepository.findByID(esl.getEquipmentID());
            makeCsvString=makeCsvStringAndReservationMatching(equipment,esl,newEsl);
            if(makeCsvString!=null) {
                csvString += makeCsvString;
            }
        }
        if(csvString.length()>initStringlength){
            writeCSV(csvString);
            FTPUploader("192.168.1.15", "cgESLUser", "cgESLPassword");
            File fileTest = new File("./src/main/resources/import_" + oldFile + ".csv");
            String fileName = fileTest.getName();
            uploadFile("./src/main/resources/import_" + oldFile + ".csv",fileName,"/Import/");
            disconnect();
        }
    }


    public String makeCsvStringAndReservationMatching(Equipment equipment, ESL esl,ESL newEsl){//매칭된 equipment 객체, 현재 esl객체, 새로 update할 esl 객체

        String csvString=new String();
        List<Reservation> reservationList;
        Reservation reservation;

        if(equipment.getEquipmentAvailable()==0){
            if (esl.getReservationID() == null)
                return null;
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+" "+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }

        else if(equipment.getEquipmentAvailable()==1){
            reservationList=reservationRepository.isInUse(equipment.getEquipmentID());
            if(esl.getReservationID()==reservationList.get(0).getReservationID())
                return null;
            else {
                newEsl.setReservationID(reservationList.get(0).getReservationID());
                eslRepository.update(newEsl);
                reservation=reservationRepository.findByID(esl.getReservationID());
                csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+reservation.getUserID().getUserName()+','+reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
            }
        }
        else if(equipment.getEquipmentAvailable()==2){
            if (esl.getReservationID() == null)
                return null;
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+recentReservation(equipment)+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }
        return csvString;
    }

    public void writeCSV(String csvString) {
        LocalDateTime now=LocalDateTime.now();
        String nowFile=now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File newCsv = new File("./src/main/resources/import_"+nowFile+".csv");
        newCsv.deleteOnExit();
        if(oldFile!=null) {
            File oldCsv = new File("./src/main/resources/import_" + oldFile + ".csv");
            oldCsv.renameTo(newCsv);
        }

        BufferedWriter bw = null; // 출력 스트림 생성
        try {
            bw = new BufferedWriter(new FileWriter(newCsv));
            // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다
            bw.write(csvString);
            // 작성한 데이터를 파일에 넣는다

            bw.newLine(); // 개행
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush(); // 남아있는 데이터까지 보내 준다
                    bw.close(); // 사용한 BufferedWriter를 닫아 준다
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        oldFile=nowFile;

    }
    
    @Override
    public String recentReservation(Equipment equipment) {
        Reservation reservation = reservationRepository.recentReservation(equipment);
        if(reservation == null){
            return "-";
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String recentStartTime = format.format(java.sql.Timestamp.valueOf(reservation.getStartTime()));

        return recentStartTime;
    }

    // param( host server ip, username, password ) 생성자
    public void FTPUploader(String host, String user, String pwd) throws Exception {
        ftpClient = new FTPClient();
        ftpClient.setDefaultPort(2121);
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftpClient.connect(host);// 호스트 연결
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftpClient.login(user, pwd);// 로그인
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
    }

    // param( 보낼파일경로+파일명, 호스트에서 받을 파일 이름, 호스트 디렉토리 )
    public void uploadFile(String localFileFullName, String fileName, String hostDir) throws Exception {
        try (InputStream input = new FileInputStream(new File(localFileFullName))) {
            this.ftpClient.storeFile(hostDir + fileName, input);
        }
    }

    public void disconnect() {
        if (this.ftpClient.isConnected()) {
            try {
                this.ftpClient.logout();
                this.ftpClient.disconnect();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }
    }

}