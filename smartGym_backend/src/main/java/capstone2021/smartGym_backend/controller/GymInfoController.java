package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.GymInfo.*;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.GymInfo;
import capstone2021.smartGym_backend.service.GymInfoService;
import capstone2021.smartGym_backend.service.GymOperationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class GymInfoController {
    private final GymInfoService gymInfoService;
    private final GymOperationInfoService gymOperationInfoService;

    @Autowired
    public GymInfoController(GymInfoService gymInfoService, GymOperationInfoService gymOperationInfoService) {
        this.gymInfoService = gymInfoService;
        this.gymOperationInfoService = gymOperationInfoService;
    }

    @CrossOrigin("*")
    @PostMapping("/gymInfo/update") //헬스장 정보 수정
    @ResponseBody
    public boolean gymInfoUpdate(@RequestBody final GymInfoDTO gymInfoDTO){
        return gymInfoService.update(gymInfoDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/gymInfo/read") //헬스장 정보 조회
    @ResponseBody
    public GymInfo gymInfoRead(){
        return gymInfoService.read();
    }

    @CrossOrigin("*")
    @PostMapping("/gymInfo/equipmentLayout/update") //헬스장 운동기구 배치도 수정
    @ResponseBody
    public boolean gymInfoEquipmentLayoutUpdate(@ModelAttribute final GymInfoEquipmentLayoutDTO gymInfoEquipmentLayoutDTO) throws IOException {
        return gymInfoService.equipmentLayoutUpdate(gymInfoEquipmentLayoutDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/gymInfo/equipmentLayout/read") //헬스장 운동기구 배치도 조회
    @ResponseBody
    public String gymInfoEquipmentLayoutRead(){
        return gymInfoService.equipmentLayoutRead();
    }

    @CrossOrigin("*")
    @PostMapping("/gymOperationInfo/update") //헬스장 운영정보 수정
    @ResponseBody
    public boolean gymOperationInfoUpdate(@RequestBody final GymOperationInfoDTO gymOperationInfoDTO){
        return gymOperationInfoService.update(gymOperationInfoDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/gymOperationInfo/read") //헬스장 운영정보 조회
    @ResponseBody
    public GymOperationInfoDTO gymOperationInfoRead(){
        return gymOperationInfoService.read();
    }

    @CrossOrigin("*")
    @PostMapping("/gymOperationInfo/holiday/create") //헬스장 운영정보 휴무일 등록
    @ResponseBody
    public boolean gymOperationInfoHolidayCreate(@RequestBody final GymHolidayCreateDTO gymHolidayCreateDTO){
        return gymOperationInfoService.createHoliday(gymHolidayCreateDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/gymOperationInfo/holiday/delete") //헬스장 운영정보 휴무일 삭제
    @ResponseBody
    public boolean gymOperationInfoHolidayDelete(@RequestBody final GymHolidayDeleteDTO gymHolidayDeleteDTO){
        return gymOperationInfoService.deleteHoliday(gymHolidayDeleteDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/gymOperationInfo/holiday/read") //헬스장 운영정보 휴무일 조회
    @ResponseBody
    public List<GymHoliday> gymOperationInfoHolidayRead(){
        return gymOperationInfoService.readHoliday();
    }
}
