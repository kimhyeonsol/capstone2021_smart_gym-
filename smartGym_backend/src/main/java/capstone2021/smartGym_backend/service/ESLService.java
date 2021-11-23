package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedRead;
import capstone2021.smartGym_backend.domain.ESL;

import java.util.List;

public interface ESLService {
    boolean eslCreate(); //ESL 생성
    boolean eslEquipmentUpdate(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO); //ESL 수정
    boolean eslDelete(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO); //ESL 삭제
    List<ESL> eslRead(); //ESL 조회
    ReturnESLDetailedRead eslDetailedRead(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO); //ESL 상세조회
    void eslReservationUpdate();
    //void currentlyChecking(); //현재 시간 기준으로 ESL 테이블 정보 갱신


}

