package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.ESL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

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
    public boolean save(ESL ESL) {
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
            ESL findESL=new ESL();
            findESL= em.find(ESL.class,ESL.getEquipmentID());
            findESL.setReservationID(ESL.getReservationID());
            return true;
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
    public ESL readByEquipmentID(Long equipmentID) {
        ESL findESL=null;
        try {
            findESL= em.find(ESL.class,equipmentID);
        }catch(PersistenceException | IllegalStateException e) {

        }
        return findESL;
    }
}
