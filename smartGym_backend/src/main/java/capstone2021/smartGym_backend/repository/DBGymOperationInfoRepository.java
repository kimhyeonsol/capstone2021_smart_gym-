package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.GymInfo.GymOperationInfoDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.GymOperationInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class DBGymOperationInfoRepository implements GymOperationInfoRepository{
    private final EntityManager em;

    public DBGymOperationInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int update(GymOperationInfo gymOperationInfo) {
        try{
            em.merge(gymOperationInfo);
            return 0;

        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return 4;
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
    public GymOperationInfo readGymOperationInfo() {
        GymOperationInfo gymOperationInfoFind = em.find(GymOperationInfo.class, 1);
        return gymOperationInfoFind;
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

    @Override
    public String readGymOperationInfoReservationDuration() {
        GymOperationInfo gymOperationInfoFind = em.find(GymOperationInfo.class, 1);
        return gymOperationInfoFind.getGymOperationInfoReservationDuration();
    }

    @Override
    public String readRegularHoliday() {
        GymOperationInfo gymOperationInfoFind = em.find(GymOperationInfo.class, 1);
        return gymOperationInfoFind.getGymOperationInfoRegularHoliday();
    }

    @Override
    public List<GymHoliday> readHolidayByMonth(int year, int month,int lastOfMonth) {
        Date startdate=new Date();
        Date enddate=new Date();

        Calendar cal= Calendar.getInstance();

        cal.set(year,month-1,1);
        startdate=cal.getTime();

        cal.set(year,month-1,lastOfMonth);
        enddate=cal.getTime();

        return em.createQuery("SELECT gh FROM GymHoliday gh WHERE gh.gymHolidayDate BETWEEN :startdate AND :enddate")
                .setParameter("startdate", startdate, TemporalType.DATE)
                .setParameter("enddate", enddate, TemporalType.DATE)
                .getResultList();
    }
}
