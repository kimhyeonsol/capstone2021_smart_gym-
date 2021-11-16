package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.List;

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

    @Override
    public List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(long id) {
        Equipment findEquipment = em.find(Equipment.class, id);
        return em.createQuery("SELECT new capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO(r.reservationID, r.userID.userID, r.equipmentID.equipmentName, r.equipmentID.equipmentNameNth, r.startTime, r.endTime) FROM Reservation r WHERE r.equipmentID = :equipment ORDER BY r.reservationID DESC", ReturnReservationReadByEquipmentDTO.class)
                .setParameter("equipment", findEquipment).getResultList();
    }

    @Override
    public List<Reservation> readReservationByUserIDAndDay(String userID, int year, int month, int day) {
        LocalDateTime selectedDate=LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime nextDate=LocalDateTime.of(year,month,day+1,0,0,0);


        return em.createQuery("SELECT r FROM Reservation r WHERE (r.startTime BETWEEN :selectedDate AND :nextDate)AND(r.userID.userID=:userID)")
                .setParameter("selectedDate", selectedDate)
                .setParameter("nextDate", nextDate)
                .setParameter("userID", userID )
                .getResultList();
    }

}