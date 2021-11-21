package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLUpdateDTO;
import capstone2021.smartGym_backend.domain.ESL;
import capstone2021.smartGym_backend.domain.GymInfo;
import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ESLServiceImpl implements ESLService{
    private final ESLRepository eslRepository;
    private final ReservationRepository reservationRepository;
    private final GymOperationInfoRepository gymOperationInfoRepository;
    private final EquipmentRepository equipmentRepository;
    private final AllowedUserRepository allowedUserRepository;

    @Autowired
    public ESLServiceImpl(ESLRepository eslRepository, ReservationRepository reservationRepository, GymOperationInfoRepository gymOperationInfoRepository, EquipmentRepository equipmentRepository, AllowedUserRepository allowedUserRepository) {
        this.eslRepository = eslRepository;
        this.reservationRepository = reservationRepository;
        this.gymOperationInfoRepository=gymOperationInfoRepository;
        this.equipmentRepository=equipmentRepository;
        this.allowedUserRepository=allowedUserRepository;
    }

    @Override
    public boolean ESLCreate() {
        ESL esl = new ESL();
        esl.setEquipmentID(null);
        esl.setReservationID(null);

        return eslRepository.create(esl);
    }

    @Override
    public boolean ESLUpdate(ESLUpdateDTO eslUpdateDTO) {
        ESL esl = new ESL();
        esl.setESLID(eslUpdateDTO.getEslID());
        esl.setEquipmentID(eslUpdateDTO.getEquipmentID());

        ESL findESL = eslRepository.findByID(eslUpdateDTO.getEslID());
        esl.setReservationID(findESL.getReservationID());

        return eslRepository.update(esl);
    }

    @Override
    public boolean ESLDelete(ESLDeleteDTO eslDeleteDTO) {
        ESL findESL = eslRepository.findByID(eslDeleteDTO.getEslID());

        return eslRepository.delete(findESL);
    }

    @Override
    public List<ESL> ESLRead() {
        return eslRepository.read();
    }

    @Override
    public boolean currentlyChecking() {


        return false;
    }

    public void writeCSV() {
        File csv = new File("./src/main/resources/import_20211111.csv");
        BufferedWriter bw = null; // 출력 스트림 생성
        try {
            bw = new BufferedWriter(new FileWriter(csv, true));
            // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다

//            GymInfo gymInfo = gymInfoRepository.read();
//            String aData = "";
//            aData = gymInfo.getGymInfoName() + "," + gymInfo.getGymInfoPhoneNumber() + "," + gymInfo.getGymInfoAddress();
//            // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
//            bw.write(aData);
//            // 작성한 데이터를 파일에 넣는다
//            bw.newLine(); // 개행

//            Reservation reservation=reservationRepository.findByID(1L);
//            ArrayList<String> dataList=new ArrayList<String>();
////            String[] data1=new String[3];
////            for(int i=0;i<data1.length;i++) {
////                data1[i] = new String("");
////            }
//
////            dataList.add(Long.toString(reservation.getReservationID()));
////            dataList.add(reservation.getUserID().getUserID());
//
//            for (int i = 0; i < dataList.size(); i++) {
//                String data = dataList.get(i);
//
//                // 한 줄에 넣을 각 데이터 사이에 ,를 넣는다
//                bw.write("**");
//                // 작성한 데이터를 파일에 넣는다
//                bw.newLine(); // 개행
//            }
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
    }

}
