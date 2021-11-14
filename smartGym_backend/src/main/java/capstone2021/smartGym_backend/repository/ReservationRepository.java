package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.domain.Reservation;

public interface ReservationRepository {
    Boolean reservationCreate(Reservation reservation);
}
