package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
import capstone2021.smartGym_backend.domain.ESL;

import java.util.List;

public interface ESLService {
    boolean eslCreate(); //ESL 생성
    boolean eslUpdate(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO); //ESL 수정
    boolean eslDelete(ESLDeleteDTO eslDeleteDTO); //ESL 삭제
    List<ESL> eslRead(); //ESL 조회
    boolean currentlyChecking(); //현재 시간 기준으로 ESL 정보 갱신
    void eslReservationUpdate();
    //void writeCSV();
}//csv 파일
//    boolean equipmentAdd();//기구(esl)추가
//    boolean equipmentRemove();//기구(esl)삭제
//    boolean
    //void currentlyChecking();//현재
    //boolean ESLTableUpdate(ESL ESL);
    //void writeCSV();
//}
