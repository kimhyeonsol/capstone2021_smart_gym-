package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Manager.ManagerSaveLoginStatusDTO;
import capstone2021.smartGym_backend.DTO.Manager.ManagerLoginDTO;
import capstone2021.smartGym_backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public int managerLogin(@RequestBody final ManagerLoginDTO managerLoginDTO){
        return managerService.managerLogin(managerLoginDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/manager/saveLoginStatus") //로그인&로그아웃 상태 저장
    @ResponseBody
    public boolean managerSaveLoginStatus(@RequestBody final ManagerSaveLoginStatusDTO managerSaveLoginStatusDTO){
        return managerService.managerSaveLoginStatus(managerSaveLoginStatusDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/manager/isLogin") //로그인 여부 조회
    @ResponseBody
    public boolean managerIsLogin(){
        return managerService.managerIsLogin();
    }
}
