package capstone2021.smartGym_backend.repository;

import java.util.List;

public interface StatisticsRepository {
    List statisticsMembership(String year);
    List statisticsReservation(String year);
    List statisticsEquipment(String year);
}
