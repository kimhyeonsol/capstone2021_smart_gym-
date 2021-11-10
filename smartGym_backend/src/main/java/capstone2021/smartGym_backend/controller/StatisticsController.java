package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Statistics.StatisticsDTO;
import capstone2021.smartGym_backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @CrossOrigin("*")
    @PostMapping("/statistics/membership") //회원가입 수 통계
    @ResponseBody
    public List statisticsMembership(@RequestBody final StatisticsDTO statisticsDTO){
        return statisticsService.statisticsMembership(statisticsDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/statistics/reservation") //예약 수 통계
    @ResponseBody
    public List statisticsReservation(@RequestBody final StatisticsDTO statisticsDTO){
        return statisticsService.statisticsReservation(statisticsDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/statistics/equipment") //인기 운동기구 TOP5 통계
    @ResponseBody
    public List statisticsEquipment(@RequestBody final StatisticsDTO statisticsDTO){
        return statisticsService.statisticsEquipment(statisticsDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/statistics/equipmentCategory") //운동기구 부위별 예약 수 통계
    @ResponseBody
    public List statisticsEquipmentCategory(@RequestBody final StatisticsDTO statisticsDTO){
        return statisticsService.statisticsEquipmentCategory(statisticsDTO);
    }
}
