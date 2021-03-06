package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.ESL.*;
import capstone2021.smartGym_backend.DTO.Reservation.ReservationCancleDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedReadDTO;
import capstone2021.smartGym_backend.domain.ESL;
import capstone2021.smartGym_backend.domain.Equipment;

import java.util.List;

public interface ESLService {
    int eslCreate(ESLCreateDTO eslCreateDTO); //ESL 생성
    int eslEquipmentUpdate(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO); //ESL 수정
    boolean eslEquipmentUnmatch(ESLEquipmentMatchingDTO eslEquipmentMatchingDTO);//매칭 해제
    boolean eslDelete(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO); //ESL 삭제
    List<ESL> eslRead(); //ESL 조회
    ReturnESLDetailedReadDTO eslDetailedRead(ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO); //ESL 상세조회
    void eslReservationUpdate() throws Exception;
    String makeCsvStringAndReservationMatching(Equipment equipment, ESL esl,ESL newEsl);
    void writeCSV(String csvString);
    String recentReservation(Equipment equipment); //현재 시간 기준 가장 최근 예약 조회(모두 사용 가능 상태일 경우)
    List<Equipment> readMatchableExerciser();
    boolean eslEquipmentMatchCheck(ESLEquipmentMatchCheckDTO eslEquipmentMatchCheckDTO); //ESL과 운동기구가 매칭 상태인지 체크
    List<Equipment> readMatchableExerciserLikeEquipmentName(EslReadLikeEquipmentNameDTO eslReadLikeEquipmentNameDTO);
    void eslUpdateWhenUpdateEquipment(Long equipmentID);
    void eslUpdateWhenCancleReservation(Long equipmentID);
}

