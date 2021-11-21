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
    public boolean create(ESL ESL) {
        try {
            em.persist(ESL);
            return true;
        }catch(PersistenceException | IllegalStateException e){
            return false;
        }
    }

    @Override
    public boolean update(ESL ESL) {
        try {
            ESL findESL=null;
            findESL = findByID(ESL.getESLID());
            if(findESL!=null) {
                findESL.setEquipmentID(ESL.getEquipmentID());
                findESL.setReservationID(ESL.getReservationID());
                return true;
            }
            else
                return false;
        }catch(PersistenceException | IllegalStateException e) {
            return false;
        }
    }

    @Override
    public boolean delete(ESL ESL) {
        try {
            em.remove(ESL);
            return true;
        } catch (PersistenceException | IllegalStateException e) {
            return false;
        }
    }

    @Override
    public List<ESL> read() {
        return em.createQuery("SELECT esl FROM ESL esl", ESL.class)
                .getResultList();
    }

    @Override
    public ESL findByID(long id) {
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
}
