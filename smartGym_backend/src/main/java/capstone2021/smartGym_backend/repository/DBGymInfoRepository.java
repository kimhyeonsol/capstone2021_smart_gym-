package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.GymInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@Repository
public class DBGymInfoRepository implements GymInfoRepository{
    private final EntityManager em;

    public DBGymInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int update(GymInfo gymInfo) {
        gymInfo.setGymInfoCongestion(read().getGymInfoCongestion());
        try{
            em.merge(gymInfo);
            return 0;

        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return 2;
        }
    }

    @Override
    public void updateCongestion(float congestion){
        GymInfo gymInfo=new GymInfo();
        gymInfo=read();
        gymInfo.setGymInfoCongestion(congestion);
        update(gymInfo);
    }



    @Override
    public GymInfo read() {
        return em.find(GymInfo.class, 1);
    }

    @Override
    public boolean equipmentLayoutUpdate(GymInfo gymInfo) {
        try{
            em.merge(gymInfo);
            return true;

        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return false;
        }
    }

    @Override
    public String equipmentLayoutRead() {
        GymInfo findGymInfo = em.find(GymInfo.class, 1);

        return findGymInfo.getGymInfoEquipmentLayout();
    }
}
