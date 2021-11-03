package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Manager.ManagerLoginDTO;
import capstone2021.smartGym_backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @CrossOrigin("*")
    @PostMapping("/manager/login") //관리자 로그인
    @ResponseBody
    public boolean managerLogin(@RequestBody final ManagerLoginDTO managerLoginDTO){
        return managerService.managerLogin(managerLoginDTO);
    }
}
