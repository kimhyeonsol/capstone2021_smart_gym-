package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.EquipmentCategory;
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
        if(year.isBlank()){
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
        if(year.isBlank()){
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

    @Override
    public List statisticsEquipmentCategory(String year) {
        if(year.isBlank()){
            return em.createQuery("SELECT SUM(ec.equipmentCategoryChest) AS 가슴, SUM(ec.equipmentCategoryBack) AS 등, " +
                            "SUM(ec.equipmentCategoryNeck) AS 목, SUM(ec.equipmentCategoryStomach) AS 복부, " +
                            "SUM(ec.equipmentCategoryTriceps) AS 삼두, SUM(ec.equipmentCategoryTrapezius) AS 승모근, " +
                            "SUM(ec.equipmentCategoryShoulder) AS 어깨, SUM(ec.equipmentCategoryAerobic) AS 유산소, " +
                            "SUM(ec.equipmentCategoryBiceps) AS 이두, SUM(ec.equipmentCategoryLowerBody) AS 하체, " +
                            "SUM(ec.equipmentCategoryWaist) AS 허리, SUM(ec.equipmentCategoryEtc) AS 기타 " +
                    "FROM EquipmentCategory ec, Reservation r WHERE r.equipmentID = ec.equipmentCategoryID")
                    .getResultList();
        }

        String sql1 = "SELECT SUM(ec.equipmentCategoryChest) AS 가슴, SUM(ec.equipmentCategoryBack) AS 등, " +
                "SUM(ec.equipmentCategoryNeck) AS 목, SUM(ec.equipmentCategoryStomach) AS 복부, " +
                "SUM(ec.equipmentCategoryTriceps) AS 삼두, SUM(ec.equipmentCategoryTrapezius) AS 승모근, " +
                "SUM(ec.equipmentCategoryShoulder) AS 어깨, SUM(ec.equipmentCategoryAerobic) AS 유산소, " +
                "SUM(ec.equipmentCategoryBiceps) AS 이두, SUM(ec.equipmentCategoryLowerBody) AS 하체, " +
                "SUM(ec.equipmentCategoryWaist) AS 허리, SUM(ec.equipmentCategoryEtc) AS 기타 " +
                "FROM EquipmentCategory ec, Reservation r WHERE r.equipmentID = ec.equipmentCategoryID AND function('date_format', r.startTime, '%Y') = ";
        String qlString = sql1 + year;

        return em.createQuery(qlString).getResultList();
    }
}
