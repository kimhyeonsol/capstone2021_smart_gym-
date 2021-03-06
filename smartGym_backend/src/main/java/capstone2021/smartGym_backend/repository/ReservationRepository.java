package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    Boolean reservationCreate(Reservation reservation);
    List<Reservation> readReservationByUserIDAndDay(String UserID, int year, int month, int day);
    List<Reservation> readReservationByEquipmentAndDay(Long equipmentID, int year, int month, int day);
    List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(long id);
    Boolean delete(Long reservationID);
    Reservation findByID(Long reservationID);
    int deleteWhenEquipmentUpdateDelete(Equipment equipment); //운동기구 고장 시 예약 삭제
    boolean nullWhenEquipmentDelete(Equipment equipment); //운동기구 삭제 시 이전 예약 null처리
    List<Reservation> isInUse(Long equipmentID);
    Reservation recentReservation(Equipment equipment);
    List<Reservation> reservationDuplCheck(ReservationCreateDTO reservationCreateDTO);
    Boolean updateReservationTime(Long reservationID);
}
