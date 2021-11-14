package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationCreateDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
}
