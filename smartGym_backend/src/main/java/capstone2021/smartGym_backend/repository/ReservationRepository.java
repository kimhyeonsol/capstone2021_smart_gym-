package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    Boolean reservationCreate(Reservation reservation);
    List<Reservation> readReservationByUserIDAndDay(String UserID, int year, int month, int day);
}
