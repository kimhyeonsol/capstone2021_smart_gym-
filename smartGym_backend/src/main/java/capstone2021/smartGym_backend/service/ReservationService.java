package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentSearchByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Reservation.*;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<String> calAvailableDate(); //헬스장 예약 가능일 반환
    List<Integer> calRegularHolidayDate(CalHolidayDateDTO calRegularHolidayDateDTO);//헬스장 정기 휴무일 반환
    List<Integer> calHolidayDate(CalHolidayDateDTO calRegularHolidayDateDTO);//헬스장 휴무일 반환
    EquipmentSearchByCategoryDTO searchEquipmentByCategory();//카테고리별 운동기구 조회
    int makeReservation(ReservationCreateDTO reservationCreateDTO);//예약하기
    List<SelectedDayReservationDTO> readMyReservationOfSelectedDay(ReservationReadSelectedDayDTO reservationReadSelectedDayDTO);//선택한 날짜 내 예약 조회하기
    List<SelectedDayReservationDTO> readEquipmentReservationOfSeletedDay(ReservationReadBySelectedDayAndEquipmentDTO reservationReadBySelectedDayAndEquipmentDTO);//선택한 날짜 운동기굽 별 조회하기
    List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(ReservationReadByEquipmentDTO reservationReadByEquipmentDTO); //운동기구 별 예약 이력 조회
    Boolean cancleReservation(ReservationCancleDTO reservationCancleDTO);//예약 취소하기
    Boolean terminateReservation(ReservationCancleDTO reservationCancleDTO);//예약 종료하기
    ReservationReadOperatingTimeDTO readOperatingTime();//헬스장 운영 시간 조회
    List<Reservation> equipmentIsinUseCurrently(Long equipmentID);//현재 시간 기준으로 해당 운동기구가 사용중인지 조회
    void equipmentAvailableCheck();//현재 운동기구가 사용 가능한지 체크해서 available값 지정해주는 메소드

    // 예약 확인
}
