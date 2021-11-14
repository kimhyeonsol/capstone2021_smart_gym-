package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentSearchByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Reservation.CalHolidayDateDTO;
import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.DTO.Reservation.ReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<String> calAvailableDate(); //헬스장 예약 가능일 반환
    List<Integer> calRegularHolidayDate(CalHolidayDateDTO calRegularHolidayDateDTO);//헬스장 정기 휴무일 반환
    List<Integer> calHolidayDate(CalHolidayDateDTO calRegularHolidayDateDTO);//헬스장 휴무일 반환
    EquipmentSearchByCategoryDTO searchEquipmentByCategory();
    int makeReservation(ReservationCreateDTO reservationCreateDTO);
    List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(ReservationReadByEquipmentDTO reservationReadByEquipmentDTO); //운동기구 별 예약 이력 조회
}
