package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedRead;
import capstone2021.smartGym_backend.domain.ESL;
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
    @GetMapping("/esl/create") //ESL 생성
    @ResponseBody
    public boolean eslCreate() {
        return eslService.eslCreate();
    }

    @CrossOrigin("*")
    @PostMapping("/esl/update") //ESL 수정
    @ResponseBody
    public boolean eslUpdate(@RequestBody final ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        return eslService.eslEquipmentUpdate(eslEquipmentMatchingDTO);
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
    public ReturnESLDetailedRead eslDetailedRead(@RequestBody final ESLDeleteDetailedReadDTO eslDeleteDetailedReadDTO) {
        return eslService.eslDetailedRead(eslDeleteDetailedReadDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/esl/eslEquipmentUpdate") //ESL 운동기구 매칭
    @ResponseBody
    public Boolean eslEquipmentUpdate(@RequestBody final ESLEquipmentMatchingDTO eslEquipmentMatchingDTO) {
        return eslService.eslEquipmentUpdate(eslEquipmentMatchingDTO);
    }




}

