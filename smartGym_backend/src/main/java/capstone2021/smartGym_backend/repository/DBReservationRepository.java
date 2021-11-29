package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.Reservation;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;
import java.time.Duration;
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
    public Boolean delete(Long reservationID) {
        Reservation findReservation=null;
        findReservation=findByID(reservationID);
        if(findReservation!=null) {
            em.remove(findReservation);
            return true;
        }
        return false;
    }

    @Override
    public Reservation findByID(Long reservationID) {
        Reservation findReservation=null;
        findReservation=em.find(Reservation.class,reservationID);
        return findReservation;
    }

    @Override
    public List<Reservation> isInUse(Long equipmentID) {
        LocalDateTime now= LocalDateTime.now();
        List<Reservation> findReservation;
        findReservation = em.createQuery("SELECT r FROM Reservation r WHERE function('date_format', :now, '%Y-%m-%d %H:%i:%s') >= r.startTime AND function('date_format', :now, '%Y-%m-%d %H:%i:%s') <= r.endTime AND r.equipmentID.equipmentID = :equipment", Reservation.class)
                .setParameter("now", now).setParameter("equipment", equipmentID).getResultList();
        return findReservation;
    }

    @Override
    public Reservation recentReservation(Equipment equipment) {
        LocalDateTime now= LocalDateTime.now();
        long smallTimeDifference = -1;
        Reservation resultReservation = null;

        List<Reservation> reservations = em.createQuery("SELECT r FROM Reservation r WHERE r.equipmentID = :equipment AND :now <= r.startTime", Reservation.class)
                .setParameter("now", now).setParameter("equipment", equipment).getResultList();

        for(Reservation reservation:reservations) {
            Duration duration = Duration.between(now, reservation.getStartTime());
            long timeDifference = duration.getSeconds();

            if(smallTimeDifference < 0){
                smallTimeDifference = timeDifference;
                resultReservation = reservation;
            }
            else if(smallTimeDifference > timeDifference){
                smallTimeDifference = timeDifference;
                resultReservation = reservation;
            }
        }

        return resultReservation;
    }

    @Override
    public List<Reservation> reservationDuplCheck(ReservationCreateDTO reservationCreateDTO) {
        List<Reservation> reservations=null;
        reservations = em.createQuery("SELECT r FROM Reservation r WHERE function('date_format', :startTime, '%Y-%m-%d %H:%i:%s') <= r.endTime AND function('date_format', :endTime, '%Y-%m-%d %H:%i:%s') >= r.startTime AND :equipmentID=r.equipmentID.equipmentID", Reservation.class)
                .setParameter("startTime", reservationCreateDTO.getStartTime())
                .setParameter("endTime", reservationCreateDTO.getEndTime())
                .setParameter("equipmentID",reservationCreateDTO.getEquipmentID())
                .getResultList();

        return reservations;
    }

    @Override
    public Boolean updateReservationTime(Long reservationID) {
        Reservation reservation=null;
        LocalDateTime now=LocalDateTime.now();
        try {
            reservation = em.find(Reservation.class, reservationID);
            reservation.setEndTime(now);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteWhenEquipmentDelete(Equipment equipment) {
        List<Reservation> reservations;

        reservations = em.createQuery("SELECT r FROM Reservation r WHERE r.equipmentID = :equipment", Reservation.class)
                .setParameter("equipment", equipment).getResultList();

        try {
            for(Reservation reservation : reservations){ //예약 다 삭제
                delete(reservation.getReservationID());
            }
            return true;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("예약 delete 오류");
            return false;
        }
    }

    @Override
    public int deleteWhenEquipmentUpdate(Equipment equipment) {
        List<Reservation> reservations;
        LocalDateTime now = LocalDateTime.now();

        reservations = em.createQuery("SELECT r FROM Reservation r WHERE function('date_format', :now, '%Y-%m-%d %H:%i:%s') <= r.startTime AND r.equipmentID = :equipment", Reservation.class)
                .setParameter("now", now).setParameter("equipment", equipment).getResultList();
        try {
            for(Reservation reservation : reservations){ //현재시간 이후의 예약 삭제
                delete(reservation.getReservationID());
            }
            return 0;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("예약 delete 오류");
            return 3;
        }
    }


    @Override
    public List<Reservation> readReservationByUserIDAndDay(String userID, int year, int month, int day) {
        LocalDateTime selectedDate=LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime nextDate=LocalDateTime.of(year,month,day,23,59,59);


        return em.createQuery("SELECT r FROM Reservation r WHERE (r.startTime BETWEEN :selectedDate AND :nextDate)AND(r.userID.userID=:userID)")
                .setParameter("selectedDate", selectedDate)
                .setParameter("nextDate", nextDate)
                .setParameter("userID", userID )
                .getResultList();
    }

    @Override
    public List<Reservation> readReservationByEquipmentAndDay(Long equipmentID, int year, int month, int day) {

        LocalDateTime selectedDate=LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime nextDate=LocalDateTime.of(year,month,day,23,59,59);


        return em.createQuery("SELECT r FROM Reservation r WHERE (r.startTime BETWEEN :selectedDate AND :nextDate)AND(r.equipmentID.equipmentID=:equipmentID)")
                .setParameter("selectedDate", selectedDate)
                .setParameter("nextDate", nextDate)
                .setParameter("equipmentID", equipmentID )
                .getResultList();
    }

}
