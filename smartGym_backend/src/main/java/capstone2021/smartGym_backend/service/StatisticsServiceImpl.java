package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Statistics.StatisticsDTO;
import capstone2021.smartGym_backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService{
    private final StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public List statisticsMembership(StatisticsDTO statisticsDTO) {
        String year = statisticsDTO.getYear();

        return statisticsRepository.statisticsMembership(year);
    }

    @Override
    public List statisticsReservation(StatisticsDTO statisticsDTO) {
        String year = statisticsDTO.getYear();

        return statisticsRepository.statisticsReservation(year);
    }

    @Override
    public List statisticsEquipment(StatisticsDTO statisticsDTO) {
        String year = statisticsDTO.getYear();

        return statisticsRepository.statisticsEquipment(year);
    }
}
