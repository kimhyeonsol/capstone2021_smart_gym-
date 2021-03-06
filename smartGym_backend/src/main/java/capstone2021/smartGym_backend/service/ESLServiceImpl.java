package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.*;
import capstone2021.smartGym_backend.DTO.Reservation.ReservationCancleDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedReadDTO;
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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    private FTPClient ftpClient = null; //FTP 관련 변수들
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
    public int eslCreate(ESLCreateDTO eslCreateDTO) {
        if(eslCreateDTO.getEslID().isBlank() || eslCreateDTO.getEslID() == null){
            return 1;
        }

        ESL findESL = eslRepository.findByID(eslCreateDTO.getEslID());
        if(findESL != null){
            return 2;
        }

        ESL esl = new ESL();
        esl.setEslID(eslCreateDTO.getEslID());
        esl.setEquipmentID(null);
        esl.setReservationID(null);

        return eslRepository.create(esl);
    }

    @Override
    public int eslEquipmentUpdate(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        ESL newEsl = new ESL();
        Equipment equipment;
        String csvString=new String();
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";

        try {
            //esl 찾기
            ESL esl = eslRepository.findByID(eslEquipmentMatchingDTO.getEslID());
            if(esl==null)
                return 1;

            //만약 원래 매칭되었던 운동기구가 있다면
            if(esl.getEquipmentID()!=null) {
                //운동기구의 esl 매칭 해제
                equipment = equipmentRepository.findByID(esl.getEquipmentID());
                equipment.setEslID(null);
            }

            newEsl.setEslID(eslEquipmentMatchingDTO.getEslID());
            //새로 매칭할 운동기구 객체 찾기
            equipment = equipmentRepository.findByID(eslEquipmentMatchingDTO.getEquipmentID());
            //만약 운동기구 아이디 잘못됐으면 2 반환
            if(equipment==null)
                return 2;
            //아직 esl 매칭 안된 운동기구 목록 조회
            List<Equipment> matchableEquipmentList=readMatchableExerciser();
            //만약 운동기구에 이미 esl이 매칭이 되었다면
            if(!matchableEquipmentList.contains(equipment)){
                //매칭해제
                esl = eslRepository.findByID(equipment.getEslID());
                esl.setEquipmentID(null);
                //운동기구의 eslID 업데이트
                equipment.setEslID(eslEquipmentMatchingDTO.getEslID());
            }
            //esl에 운동기구 아이디 업데이트
            newEsl.setEquipmentID(eslEquipmentMatchingDTO.getEquipmentID());

            csvString+= makeCsvStringAndEquipmentMatching(equipment, newEsl);//새로 매칭된 운동기구,원래 esl,새로운 esl
            writeCSV(csvString);
            FTPUploader("192.168.1.15", "cgESLUser", "cgESLPassword");
            File fileTest = new File("./src/main/resources/import_" + oldFile + ".csv");
            String fileName = fileTest.getName();
            uploadFile("./src/main/resources/import_" + oldFile + ".csv",fileName,"/Import/");
            disconnect();
            return 0;
        }catch (Exception e){
            return 3;
        }
    }

    @Override
    public boolean eslEquipmentUnmatch(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        Equipment equipment;
        ESL esl = eslRepository.findByID(eslEquipmentMatchingDTO.getEslID());
        try {
            equipment = equipmentRepository.findByID(esl.getEquipmentID());
            if(equipment==null)
                return false;
            eslRepository.updateUnmatch(equipment,esl);
            return true;
        }catch (Exception e){
            return false;
        }
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

        equipment.setEslID(newEsl.getEslID());
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
    public ReturnESLDetailedReadDTO eslDetailedRead(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO) {
        ReturnESLDetailedReadDTO returnESLDetailedReadDTO = new ReturnESLDetailedReadDTO();

        if(eslDeleteDetailedReadDTO.getEslID() == null) { //매칭된 운동기구가 없는 경우
            returnESLDetailedReadDTO.setEquipmentAvailable(4);
            return returnESLDetailedReadDTO;
        }

        // 해당 아이디 esl 객체 찾기
        ESL findESL = eslRepository.findByID(eslDeleteDetailedReadDTO.getEslID());
        if(findESL == null){ //없는 ESL일 경우
            returnESLDetailedReadDTO.setEquipmentAvailable(3);
            return returnESLDetailedReadDTO;
        }

        if(findESL.getEquipmentID() == null){ //매칭된 운동기구가 없는 경우
            returnESLDetailedReadDTO.setEquipmentAvailable(4);
            return returnESLDetailedReadDTO;
        }

        //해당 equipment 찾기
        Equipment findEquipment = equipmentRepository.findByID(findESL.getEquipmentID());
        //헬스장 정보
        GymInfo findGymInfo = gymInfoRepository.read();


        //띄워줄 아이디
        returnESLDetailedReadDTO.setEslID(findESL.getEslID());
        //헬스장 이름
        returnESLDetailedReadDTO.setGymInfoName(findGymInfo.getGymInfoName());
        //운동기구 이름
        returnESLDetailedReadDTO.setEquipmentName(findEquipment.getEquipmentName());
        //운동기구 순서
        returnESLDetailedReadDTO.setEquipmentNameNth(findEquipment.getEquipmentNameNth());
        //운동기구 qr코드세팅
        returnESLDetailedReadDTO.setEquipmentQRCode(findEquipment.getEquipmentQRCode());

        if(findEquipment.getEquipmentAvailable() == 0){ //기구 고장
            returnESLDetailedReadDTO.setUserName("");
            returnESLDetailedReadDTO.setStartTime("");
            returnESLDetailedReadDTO.setEndTime("");
            returnESLDetailedReadDTO.setEquipmentAvailable(0);
        }

        // 기존에 구현된
        // available 값이 1초마다 변동되는 메소드를 그냥 이용하여, 단순히 db에 접근해 값을 설정해주기에는
        // 관리자의 api 호출시간을 고려했을 때 값이 처리 되기 전 일 수 있음.
        // 따라서 본 메소드에서 따로 현재 예약 조회 따로 계산
        else { //예약 상태

            List<Reservation> findReservaton = reservationRepository.isInUse(findESL.getEquipmentID());
            //모두 사용 가능한 경우
            if(findReservaton.isEmpty()){
                returnESLDetailedReadDTO.setUserName("");
                if(reservationRepository.recentReservation(findEquipment) == null){ //최근 예약 없는 경우
                    returnESLDetailedReadDTO.setStartTime("");
                }
                else{
                    LocalDateTime startTime = reservationRepository.recentReservation(findEquipment).getStartTime();
                    String startTimeString = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));

                    returnESLDetailedReadDTO.setStartTime(startTimeString);
                }
                returnESLDetailedReadDTO.setEndTime("");
                returnESLDetailedReadDTO.setEquipmentAvailable(2);

            }
            //예약 있음
            else {
                Reservation reservation=findReservaton.get(0);
                returnESLDetailedReadDTO.setUserName(reservation.getUserID().getUserName());

                String startTime = reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                String endTime = reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));

                returnESLDetailedReadDTO.setStartTime(startTime);
                returnESLDetailedReadDTO.setEndTime(endTime);
                returnESLDetailedReadDTO.setEquipmentAvailable(1);
            }
        }

        return returnESLDetailedReadDTO;
    }


    @Override
    @Scheduled(fixedDelay = 1000)//1초마다 체크
    public void eslReservationUpdate() throws Exception {

        List<ESL> eslList = eslRepository.read();

        if(eslList.isEmpty())return;

        ESL newEsl = new ESL();

        String csvString=new String();
        String makeCsvString;
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";
        int initStringlength=csvString.length();
        for (ESL esl : eslList) {
            newEsl.setEslID(esl.getEslID());
            newEsl.setEquipmentID(esl.getEquipmentID());
            if(esl.getEquipmentID()==null)
                continue;
            Equipment equipment=equipmentRepository.findByID(esl.getEquipmentID());

            makeCsvString=makeCsvStringAndReservationMatching(equipment,esl,newEsl);
            if(makeCsvString!=null) {
                csvString += makeCsvString;
            }
        }
        if(csvString.length()>initStringlength){
            System.out.println("**");
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

        //기구고장
        if(equipment.getEquipmentAvailable()==0){
            if (esl.getReservationID()==null)
                return null;
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+" "+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }

        //기구 예약 상태인 경우
        else if(equipment.getEquipmentAvailable()==1){
            reservationList=reservationRepository.isInUse(equipment.getEquipmentID());
            if(reservationList.isEmpty())
                return null;

            System.out.println(esl.getReservationID());
            System.out.println(reservationList.get(0).getReservationID());

            if(reservationList.get(0).getReservationID().equals(esl.getReservationID()))
                return null;
            else {
                newEsl.setReservationID(reservationList.get(0).getReservationID());
                eslRepository.update(newEsl);
                reservation=reservationRepository.findByID(esl.getReservationID());
                csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+reservation.getUserID().getUserName()+','+reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
            }
        }
        else if(equipment.getEquipmentAvailable()==2){
            if (esl.getReservationID()== null)
                return null;
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+recentReservation(equipment)+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }
        return csvString;
    }

    //기구 고장났을 때
    @Override
    public void eslUpdateWhenUpdateEquipment(Long equipmentID) {
        ESL newEsl = new ESL();
        Equipment findEquipment;
        String csvString=new String();
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";

        try {
            //esl 찾기

            ESL esl = eslRepository.readByEquipmentID(equipmentID);

            if(esl==null)
                return;

            newEsl.setEslID(esl.getEslID());
            //새로 매칭할 운동기구 객체 찾기
            findEquipment = equipmentRepository.findByID(equipmentID);
            //만약 운동기구 아이디 잘못됐으면 2 반환
            if(findEquipment==null)
                return;
            newEsl.setEquipmentID(findEquipment.getEquipmentID());

            csvString+= makeCsvStringWhenUpdateEquipment(findEquipment,esl, newEsl);//새로 매칭된 운동기구,원래 esl,새로운 esl
            writeCSV(csvString);
            FTPUploader("192.168.1.15", "cgESLUser", "cgESLPassword");
            File fileTest = new File("./src/main/resources/import_" + oldFile + ".csv");
            String fileName = fileTest.getName();
            uploadFile("./src/main/resources/import_" + oldFile + ".csv",fileName,"/Import/");
            disconnect();

        }catch (Exception e){
            return;
        }
    }
    public String makeCsvStringWhenUpdateEquipment(Equipment equipment, ESL esl,ESL newEsl){//매칭된 equipment 객체, 현재 esl객체, 새로 update할 esl 객체

        String csvString=new String();
        List<Reservation> reservationList;
        Reservation reservation;

        //기구고장
        if(equipment.getEquipmentAvailable()==0){
            System.out.println(0);
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+" "+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }
        //모든 사용자 사용 가능
        else if(equipment.getEquipmentAvailable()==2){
            System.out.println(2);
            newEsl.setReservationID(null);
            eslRepository.update(newEsl);
            csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+recentReservation(equipment)+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
        }
        return csvString;
    }

    //예약 취소할 때
    @Override
    public void eslUpdateWhenCancleReservation(Long equipmentID) {
        ESL newEsl = new ESL();
        Equipment findEquipment;
        String csvString=new String();
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";

        try {
            //esl 찾기
            System.out.println(3);
            ESL esl = eslRepository.readByEquipmentID(equipmentID);
            if(esl==null)
                return;
            System.out.println(4);
            newEsl.setEslID(esl.getEslID());
            //새로 매칭할 운동기구 객체 찾기
            findEquipment = equipmentRepository.findByID(equipmentID);
            //만약 운동기구 아이디 잘못됐으면 2 반환
            if(findEquipment==null)
                return;
            System.out.println(5);
            newEsl.setEquipmentID(findEquipment.getEquipmentID());

            csvString+= makeCsvStringWhenCancleReservation(findEquipment,esl, newEsl);//새로 매칭된 운동기구,원래 esl,새로운 esl
            writeCSV(csvString);
            FTPUploader("192.168.1.15", "cgESLUser", "cgESLPassword");
            File fileTest = new File("./src/main/resources/import_" + oldFile + ".csv");
            String fileName = fileTest.getName();
            uploadFile("./src/main/resources/import_" + oldFile + ".csv",fileName,"/Import/");
            disconnect();

        }catch (Exception e){
            return;
        }
    }
    public String makeCsvStringWhenCancleReservation(Equipment equipment, ESL esl,ESL newEsl){//매칭된 equipment 객체, 현재 esl객체, 새로 update할 esl 객체

        String csvString=new String();
        List<Reservation> reservationList;
        Reservation reservation;
        System.out.println(1);
        if(equipment.getEquipmentAvailable()==2){
            System.out.println(2);
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

    @Override
    public List<Equipment> readMatchableExerciser() {
        List<Equipment> equipmentList=null;
        ArrayList<Equipment> matchableList=new ArrayList<Equipment>();
        equipmentList=equipmentRepository.readAll(0);
        for(Equipment e:equipmentList){
            if(e.getEslID()==null)
                matchableList.add(e);
        }
        return matchableList;
    }

    @Override
    public boolean eslEquipmentMatchCheck(ESLEquipmentMatchCheckDTO eslEquipmentMatchCheckDTO) {
        if(eslEquipmentMatchCheckDTO.getEslID() == null || eslEquipmentMatchCheckDTO.getEslID().isBlank() || eslEquipmentMatchCheckDTO.getEquipmentID() == null){
            return false;
        }
        if(eslRepository.findByID(eslEquipmentMatchCheckDTO.getEslID()) == null){ //없는 ESL
            return false;
        }
        if(equipmentRepository.findByID(eslEquipmentMatchCheckDTO.getEquipmentID()) == null){ //없는 운동기구
            return false;
        }

        ESL findESL = eslRepository.findByID(eslEquipmentMatchCheckDTO.getEslID());
        if(findESL.getEquipmentID() == eslEquipmentMatchCheckDTO.getEquipmentID()){
            return true;
        }

        return false;
    }

    @Override
    public List<Equipment> readMatchableExerciserLikeEquipmentName(EslReadLikeEquipmentNameDTO eslReadLikeEquipmentNameDTO) {
        return equipmentRepository.readMatchableEquipmentByLikeEquipmentName(eslReadLikeEquipmentNameDTO.getLikeEquipmentName());
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