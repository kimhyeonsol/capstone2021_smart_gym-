package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLUpdateDTO;
import capstone2021.smartGym_backend.domain.ESL;

import java.util.List;

public interface ESLService {
    boolean ESLCreate(); //ESL 생성
    boolean ESLUpdate(ESLUpdateDTO eslUpdateDTO); //ESL 수정
    boolean ESLDelete(ESLDeleteDTO eslDeleteDTO); //ESL 삭제
    List<ESL> ESLRead(); //ESL 조회
    boolean currentlyChecking(); //현재 시간 기준으로 ESL 정보 갱신
    void writeCSV();
}//csv 파일
