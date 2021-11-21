package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.ESL.ESLDeleteDTO;
import capstone2021.smartGym_backend.DTO.ESL.ESLEquipmentMatchingDTO;
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
    public boolean eslDelete(@RequestBody final ESLDeleteDTO eslDeleteDTO) {
        return eslService.eslDelete(eslDeleteDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/esl/read") //ESL 조회
    @ResponseBody
    public List<ESL> eslRead() {
        return eslService.eslRead();
    }

    @CrossOrigin("*")
    @GetMapping("/esl/csv")
    @ResponseBody
    public void eslCSV(){
        //eslService.currentlyChecking();
    }
}

