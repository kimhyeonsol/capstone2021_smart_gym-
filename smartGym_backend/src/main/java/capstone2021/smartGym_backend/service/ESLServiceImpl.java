package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDTO;
import capstone2021.smartGym_backend.domain.Equipment;

import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class ESLServiceImpl implements ESLService{

    private final ReservationRepository reservationRepository;
    private final GymOperationInfoRepository gymOperationInfoRepository;
    private final EquipmentRepository equipmentRepository;
    private final AllowedUserRepository allowedUserRepository;
    private final GymInfoRepository gymInfoRepository;
    private final ESLRepository eslRepository;
    @Autowired
    public ESLServiceImpl(ReservationRepository reservationRepository, GymOperationInfoRepository gymOperationInfoRepository, EquipmentRepository equipmentRepository, AllowedUserRepository allowedUserRepository,GymInfoRepository gymInfoRepository,ESLRepository eslRepository) {
        this.reservationRepository = reservationRepository;
        this.gymOperationInfoRepository=gymOperationInfoRepository;
        this.equipmentRepository=equipmentRepository;
        this.allowedUserRepository=allowedUserRepository;
        this.gymInfoRepository=gymInfoRepository;
        this.eslRepository=eslRepository;
    }

//
//    @Override
//    @Scheduled(fixedDelay = 60000)//1분마다 체크
//    public void currentlyChecking() {
//        List<Equipment> list=null;
//        List<Reservation> reservationList=null;
//        int available;
//        list=equipmentRepository.readAll();
//        String str="";
//
//        for(Equipment e:list) {
//            reservationList=reservationRepository.isInUse(e.getEquipmentID());
//            if(e.getEquipmentID()!=eslRepository.findByID(e.getESLID()).getEquipmentID()){
//                writeCSV();
//                break;
//            }
//            else if(!reservationList.isEmpty()) {
//                if (reservationRepository.isInUse(e.getEquipmentID()).get(0).getReservationID() != eslRepository.findByID(e.getESLID()).getReservationID()) {
//                    writeCSV();
//                    break;
//                }
//            }
//        }
//
//    }
//
//    public String newCSVString(){
//
//        List<Equipment> list=null;
//        List<Reservation> reservationList=null;
//        int available;
//        list=equipmentRepository.readAll();
//        String str="";
//
//        for(Equipment e:list){
//            ESLDTO ESLDTO=new ESLDTO();
//            available=equipmentRepository.readOfEquipmentAvailable(e.getEquipmentID());
//            if(available==0 || available==2){
//                ESLDTO.setESLID(e.getESLID());
//                ESLDTO.setEquipmentName(e.getEquipmentName());
//                ESLDTO.setEquipmentNameNth(e.getEquipmentNameNth());
//                ESLDTO.setGymInfoName(gymInfoRepository.read().getGymInfoName());
//                ESLDTO.setEquipmentQRCode(e.getEquipmentQRCode());
//                ESLDTO.setEquipmentAvailable(e.getEquipmentAvailable());
//                //reservationList=reservationRepository.isInUse(e.getEquipmentID());
//                //ESLDTO.setUserName(reservationList.get(0));
//            }
//            else if(available==1){
//                ESLDTO.setESLID(e.getESLID());
//                ESLDTO.setEquipmentName(e.getEquipmentName());
//                ESLDTO.setEquipmentNameNth(e.getEquipmentNameNth());
//                reservationList=reservationRepository.isInUse(e.getEquipmentID());
//                ESLDTO.setUserName(reservationList.get(0).getUserID().getUserName());
//                SimpleDateFormat format1=new SimpleDateFormat("HH:mm");
//                ESLDTO.setReservationStartTime(format1.format(reservationList.get(0).getStartTime()));
//                ESLDTO.setReservationEndTime(format1.format(reservationList.get(0).getEndTime()));
//                ESLDTO.setGymInfoName(gymInfoRepository.read().getGymInfoName());
//                ESLDTO.setEquipmentQRCode(e.getEquipmentQRCode());
//                ESLDTO.setEquipmentAvailable(e.getEquipmentAvailable());
//            }
//
//            str=str+ESLDTO.getESLID()+','+ESLDTO.getEquipmentName()+','+ESLDTO.getEquipmentNameNth()+','+ESLDTO.getUserName()+','+ESLDTO.getReservationStartTime()+','+ESLDTO.getReservationEndTime()+','+ESLDTO.getGymInfoName()+','+ESLDTO.getEquipmentQRCode()+','+ESLDTO.getEquipmentAvailable();
//        }
//        return str;
//    }
//
//    public void writeCSV() {
//        SimpleDateFormat format1=new SimpleDateFormat("yyyyMMDDHHmmss");
//        Date date=new Date();
//
//        File csv = new File("./src/main/resources/import"+format1.format(date)+".csv");
//        BufferedWriter bw = null; // 출력 스트림 생성
//        try {
//            bw = new BufferedWriter(new FileWriter(csv, true));
//            // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다
//            bw.write(newCSVString());
//            // 작성한 데이터를 파일에 넣는다
//            bw.newLine(); // 개행
//
////            Reservation reservation=reservationRepository.findByID(1L);
////            ArrayList<String> dataList=new ArrayList<String>();
//////            String[] data1=new String[3];
//////            for(int i=0;i<data1.length;i++) {
//////                data1[i] = new String("");
//////            }
////
//////            dataList.add(Long.toString(reservation.getReservationID()));
//////            dataList.add(reservation.getUserID().getUserID());
////
////            for (int i = 0; i < dataList.size(); i++) {
////                String data = dataList.get(i);
////
////                // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
////                bw.write("**");
////                // 작성한 데이터를 파일에 넣는다
////                bw.newLine(); // 개행
////            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bw != null) {
//                    bw.flush(); // 남아있는 데이터까지 보내 준다
//                    bw.close(); // 사용한 BufferedWriter를 닫아 준다
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
