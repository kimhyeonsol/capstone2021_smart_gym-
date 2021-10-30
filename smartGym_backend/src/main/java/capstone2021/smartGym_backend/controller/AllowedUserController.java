package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;
import capstone2021.smartGym_backend.service.AllowedUserService;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AllowedUserController {

    AllowedUserService allowedUserService;
    @Autowired
    public AllowedUserController(AllowedUserService allowedUserService) {
        this.allowedUserService = allowedUserService;
    }

    @CrossOrigin("*")
    @PostMapping("/allowedUser/login") //가입대기- 회원가입
    @ResponseBody
    public int unAllowedUserRegister(@RequestBody final AllowedUserLoginDTO allowedUserLoginDTO) throws Exception {
        return allowedUserService.allowedUserLogin(allowedUserLoginDTO);
    }

}
