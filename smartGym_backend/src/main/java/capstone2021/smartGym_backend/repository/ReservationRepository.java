package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    Boolean reservationCreate(Reservation reservation);
    List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(long id);
}
