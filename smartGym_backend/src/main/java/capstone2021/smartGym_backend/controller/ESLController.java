package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.service.ESLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ESLController {
    private final ESLService eslService;

    @Autowired
    public ESLController(ESLService eslService) {
        this.eslService = eslService;
    }

    @CrossOrigin("*")
    @GetMapping("/esl/csv")
    @ResponseBody
    public void eslCSV(){
        eslService.writeCSV();
    }
}

