package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.GymInfo.GymOperationInfoDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.GymOperationInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.text.SimpleDateFormat;
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
    public GymOperationInfoDTO read() {
        GymOperationInfo gymOperationInfoFind = em.find(GymOperationInfo.class, 1);

        GymOperationInfoDTO gymOperationInfoDTO = new GymOperationInfoDTO();
        gymOperationInfoDTO.setGymOperationInfoReservationDuration(gymOperationInfoFind.getGymOperationInfoReservationDuration());
        gymOperationInfoDTO.setGymOperationInfoRegularHoliday(gymOperationInfoFind.getGymOperationInfoRegularHoliday());

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        gymOperationInfoDTO.setGymOperationInfoOperatingStartTime(format.format(gymOperationInfoFind.getGymOperationInfoOperatingStartTime()));
        gymOperationInfoDTO.setGymOperationInfoOperatingEndTime(format.format(gymOperationInfoFind.getGymOperationInfoOperatingEndTime()));

        return gymOperationInfoDTO;
    }

    @Override
    public boolean createHoliday(GymHoliday gymHoliday) {
        List<GymHoliday> gymHolidays = em.createQuery("SELECT gh FROM GymHoliday gh WHERE gh.gymHolidayDate = :gymHoliday", GymHoliday.class)
                .setParameter("gymHoliday", gymHoliday.getGymHolidayDate()).getResultList();

        if(gymHolidays.isEmpty()){
            try{
                em.persist(gymHoliday);
                return true;
            } catch (PersistenceException | IllegalStateException e){
                System.out.println("create 오류");
                return false;
            }
        }

        System.out.println("휴무일 중복!");
        return false;
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
