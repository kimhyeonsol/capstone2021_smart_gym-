package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.ESL.ESLCreateDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchCheckDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedReadDTO;
import capstone2021.smartGym_backend.domain.ESL;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.service.ESLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ESLController {
    private final ESLService eslService;

    @Autowired
    public ESLController(ESLService eslService) {
        this.eslService = eslService;
    }

    @CrossOrigin("*")
    @PostMapping("/esl/create") //ESL 생성
    @ResponseBody
    public int eslCreate(@RequestBody final ESLCreateDTO eslCreateDTO) {
        return eslService.eslCreate(eslCreateDTO);
    }
    

    @CrossOrigin("*")
    @PostMapping("/esl/delete") //ESL 삭제
    @ResponseBody
    public boolean eslDelete(@RequestBody final ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO) {
        return eslService.eslDelete(eslDeleteDetailedReadDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/esl/read") //ESL 조회
    @ResponseBody
    public List<ESL> eslRead() {
        return eslService.eslRead();
    }

    @CrossOrigin("*")
    @PostMapping("/esl/detailedRead") //ESL 상세조회
    @ResponseBody
    public ReturnESLDetailedReadDTO eslDetailedRead(@RequestBody final ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO) {
        return eslService.eslDetailedRead(eslDeleteDetailedReadDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/esl/eslEquipmentUpdate") //ESL 운동기구 매칭
    @ResponseBody
    public int eslEquipmentUpdate(@RequestBody final ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        return eslService.eslEquipmentUpdate(eslEquipmentMatchingDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/esl/eslEquipmentUnmatch") //ESL 운동기구 매칭 해제
    @ResponseBody
    public Boolean eslEquipmentUnmatch(@RequestBody final ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        return eslService.eslEquipmentUnmatch(eslEquipmentMatchingDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/esl/readMatchableEquipmentList") //매칭 가능한 운동기구 목록 조회
    @ResponseBody
    public List<Equipment> readMatchableEquipmentList() {
        return eslService.readMatchableExerciser();
    }

    @CrossOrigin("*")
    @PostMapping("/esl/eslEquipmentMatchCheck") //ESL과 운동기구가 매칭 상태인지 아닌지 체크
    @ResponseBody
    public boolean eslEquipmentMatchCheck(@RequestBody final ESLEquipmentMatchCheckDTO eslEquipmentMatchCheckDTO) {
        return eslService.eslEquipmentMatchCheck(eslEquipmentMatchCheckDTO);
    }
}

