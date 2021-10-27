package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class DBGymOperationInfoRepository implements GymOperationInfoRepository{
    private final EntityManager em;

    public DBGymOperationInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean update(GymOperationInfo gymOperationInfo) {
        try{
            em.merge(gymOperationInfo);
            return true;

        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return false;
        }
    }

    @Override
    public GymOperationInfo read() {
        return em.find(GymOperationInfo.class, 1);
    }

    @Override
    public boolean createHoliday(GymHoliday gymHoliday) {
        try{
            em.persist(gymHoliday);
            return true;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("create 오류");
            return false;
        }
    }

    @Override
    public boolean deleteHoliday(GymHoliday gymHoliday) {
        try {
            if (em.contains(gymHoliday)) {
                em.remove(gymHoliday);
            } else {
                em.remove(em.merge(gymHoliday));
            }
            return true;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("delete 오류");
            return false;
        }
    }

    @Override
    public List<GymHoliday> readHoliday() {
        return em.createQuery("SELECT gh FROM GymHoliday gh", GymHoliday.class)
                .getResultList();
    }
}
