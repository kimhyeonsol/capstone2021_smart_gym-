package capstone2021.smartGym_backend.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DBStatisticsRepository implements StatisticsRepository{
    private final EntityManager em;

    public DBStatisticsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List statisticsMembership(String year) {
        if(year.equals("")){
            return em.createQuery("SELECT function('date_format', a.userRegisterDate, '%Y') AS 날짜, COUNT(a.userRegisterDate) AS 회원가입_수 FROM AllowedUser a GROUP BY 날짜")
                    .getResultList();
        }
        else {
            String sql1 = "SELECT function('date_format', a.userRegisterDate, '%Y-%m') AS 날짜, COUNT(a.userRegisterDate) AS 회원가입_수 FROM AllowedUser a WHERE function('date_format', a.userRegisterDate, '%Y-%m') = ";
            String sql2 = " GROUP BY 날짜";
            String qlString = sql1 + year + sql2;

            return em.createQuery(qlString).getResultList();
        }
    }

    @Override
    public List statisticsReservation(String year) {
        if(year.equals("")){
            return em.createQuery("SELECT function('date_format', r.startTime, '%Y') AS 날짜, COUNT(r.startTime) AS 예약_수 FROM Reservation r GROUP BY 날짜")
                    .getResultList();
        }
        else {
            String sql1 = "SELECT function('date_format', r.startTime, '%Y-%m') AS 날짜, COUNT(r.startTime) AS 예약_수 FROM Reservation r WHERE function('date_format', r.startTime, '%Y-%m') = ";
            String sql2 = " GROUP BY 날짜";
            String qlString = sql1 + year + sql2;

            return em.createQuery(qlString).getResultList();
        }
    }

    @Override
    public List statisticsEquipment(String year) {
        if(year.isBlank()){
            return em.createQuery("SELECT e.equipmentName, count(e.equipmentName) AS 예약_수 FROM Equipment e, Reservation r WHERE e = r.equipmentID GROUP BY e.equipmentName ORDER BY COUNT(e.equipmentName) DESC, e.equipmentName")
                    .setMaxResults(5) //개수 5개로 제한
                    .getResultList();
        }

        String sql1 = "SELECT e.equipmentName, count(e.equipmentName) AS 예약_수 FROM Equipment e, Reservation r WHERE e = r.equipmentID AND function('date_format', r.startTime, '%Y') = ";
        String sql2 = " GROUP BY e.equipmentName ORDER BY COUNT(e.equipmentName) DESC, e.equipmentName";
        String qlString = sql1 + year + sql2;

        return em.createQuery(qlString)
                .setMaxResults(5) //개수 5개로 제한
                .getResultList();
    }
}
