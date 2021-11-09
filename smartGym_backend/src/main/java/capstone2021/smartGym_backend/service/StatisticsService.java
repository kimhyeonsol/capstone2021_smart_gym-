package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Statistics.StatisticsDTO;

import java.util.List;

public interface StatisticsService {
    List statisticsMembership(StatisticsDTO statisticsDTO);
    List statisticsReservation(StatisticsDTO statisticsDTO);
    List statisticsEquipment(StatisticsDTO statisticsDTO);
}
