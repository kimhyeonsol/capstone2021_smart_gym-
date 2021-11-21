package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
import capstone2021.smartGym_backend.domain.ESL;

import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.GymInfo;
import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        ESL esl = new ESL();
        esl.setEslID(eslEquipmentMatchingDTO.getEslID());
        esl.setEquipmentID(eslEquipmentMatchingDTO.getEquipmentID());

        ESL findESL = eslRepository.findByID(eslEquipmentMatchingDTO.getEslID());
        esl.setReservationID(findESL.getReservationID());

        return eslRepository.update(esl);
    }

    @Override
    public boolean eslDelete(ESLDeleteDTO eslDeleteDTO) {
        ESL findESL = eslRepository.findByID(eslDeleteDTO.getEslID());

        return eslRepository.delete(findESL);
    }

    @Override
    public List<ESL> eslRead() {
        return eslRepository.read();
    }


    @Override
    @Scheduled(fixedDelay = 60000)//30초마다 체크
    public void eslReservationUpdate() {
        List<ESL> eslList = eslRepository.read();
        List<Reservation> reservationList;
        SimpleDateFormat format1=new SimpleDateFormat("HH:mm");

        String csvString=new String();
        csvString="esl_id,equipment_name,user_name,reservation_start_time,reservation_end_time,gym_info_name,equipment_QR_code,equipment_available\n";
        int initStringlength=csvString.length();

        for (ESL esl : eslList) {

            ESL newEsl = new ESL();
            newEsl.setEslID(esl.getEslID());
            newEsl.setEquipmentID(esl.getEquipmentID());

            reservationList = reservationRepository.isInUse(esl.getEquipmentID());

            if(!reservationList.isEmpty()){//현재 운동기구 사용 중
                if(esl.getReservationID()==reservationList.get(0).getReservationID())
                    return;
                else {
                    newEsl.setReservationID(reservationList.get(0).getReservationID());
                    eslRepository.update(newEsl);
                    Equipment equipment=equipmentRepository.findByID(esl.getEquipmentID());
                    Reservation reservation=reservationRepository.findByID(esl.getReservationID());

                    csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+reservation.getUserID().getUserName()+','+reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"))+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
                }
            }
            else if (reservationList.isEmpty() && esl.getReservationID() != null) {//현재 운동기구 미사용
                if (esl.getReservationID() == null)
                    return;
                else {

                    newEsl.setReservationID(null);
                    eslRepository.update(newEsl);
                    Equipment equipment=equipmentRepository.findByID(esl.getEquipmentID());

                    if(equipment.getEquipmentAvailable()==0)
                        csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+" "+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
                    else if(equipment.getEquipmentAvailable()==2)
                        csvString=csvString+esl.getEslID()+','+equipment.getEquipmentName()+' '+equipment.getEquipmentNameNth()+','+" "+','+" "+','+recentReservation(equipment)+','+gymInfoRepository.read().getGymInfoName()+','+equipment.getEquipmentQRCode()+','+equipment.getEquipmentAvailable()+"\n";
                }
            }
        }
        if(csvString.length()>initStringlength){
            writeCSV(csvString);
        }
    }


    public void writeCSV(String csvString) {
        LocalDateTime now=LocalDateTime.now();
        String nowFile=now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        File newCsv = new File("./src/main/resources/import"+nowFile+".csv");
        if(oldFile!=null) {
            File oldCsv = new File("./src/main/resources/import" + oldFile + ".csv");
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

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String recentStartTime = format.format(java.sql.Timestamp.valueOf(reservation.getStartTime()));

        return recentStartTime;
    }
}
