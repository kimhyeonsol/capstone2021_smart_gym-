package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
public class DBReservationRepository implements ReservationRepository{

    @PersistenceContext// EntityManagerFactory가 DI 할 수 있도록 어노테이션 설정
    private final EntityManager em;

    @Autowired
    public DBReservationRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Boolean reservationCreate(Reservation reservation) {
        try{
            em.persist(reservation);
            return true;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("create 오류");
            return false;
        }
    }
}
