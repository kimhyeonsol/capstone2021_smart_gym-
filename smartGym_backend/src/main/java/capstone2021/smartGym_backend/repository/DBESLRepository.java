package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.ESL;
import capstone2021.smartGym_backend.domain.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DBESLRepository implements ESLRepository {
    @PersistenceContext// EntityManagerFactory가 DI 할 수 있도록 어노테이션 설정
    private final EntityManager em;

    @Autowired
    public DBESLRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int create(ESL esl) {
        try {
            em.persist(esl);
            return 0;
        }catch(PersistenceException | IllegalStateException e){
            return 3;
        }
    }

    @Override
    public boolean update(ESL esl) {
        try {
            ESL findESL=null;
            findESL = findByID(esl.getEslID());
            if(findESL!=null) {
                findESL.setEquipmentID(esl.getEquipmentID());
                findESL.setReservationID(esl.getReservationID());
                return true;
            }
            else
                return false;
        }catch(PersistenceException | IllegalStateException e) {
            return false;
        }
    }

    @Override
    public boolean delete(ESL esl) {
        try {
            em.remove(esl);
            return true;
        } catch (PersistenceException | IllegalStateException e) {
            return false;
        }
    }

    @Override
    public boolean updateWhenEquipmentDelete(long id) {
        ESL esl = em.createQuery("SELECT esl FROM ESL esl WHERE esl.equipmentID = :id", ESL.class)
                .setParameter("id", id).getSingleResult();

        esl.setEquipmentID(null);
        esl.setReservationID(null);

        try{
            em.merge(esl);
            return true;

        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return false;
        }
    }

    @Override
    public List<ESL> read() {
        return em.createQuery("SELECT esl FROM ESL esl", ESL.class)
                .getResultList();
    }

    @Override
    public ESL findByID(String id) {
        return em.find(ESL.class, id);
    }

    @Override
    public ESL readByEquipmentID(Long equipmentID) {
        ESL findESL=null;
        try {
            findESL= em.find(ESL.class,equipmentID);
        }catch(PersistenceException | IllegalStateException e) {

        }
        return findESL;
    }

    @Override
    public boolean updateUnmatch(Equipment equipment,ESL esl) {
        try {
            equipment.setEslID(null);
            esl.setEquipmentID(null);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
