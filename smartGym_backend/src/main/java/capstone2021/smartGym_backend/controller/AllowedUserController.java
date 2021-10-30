package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindIDDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindPWDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.service.AllowedUserService;
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
    @PostMapping("/allowedUser/login") //가입승인- 로그인
    @ResponseBody
    public int allowedUserLogin(@RequestBody final AllowedUserLoginDTO allowedUserLoginDTO)  {
        return allowedUserService.allowedUserLogin(allowedUserLoginDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/allowedUser/findID") //가입승인- 아이디찾기
    @ResponseBody
    public String allowedUserFindID(@RequestBody final AllowedUserFindIDDTO allowedUserFindIDDTO) {
        return allowedUserService.allowedUserFindID(allowedUserFindIDDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/allowedUser/findPW") //가입승인- 비밀번호찾기
    @ResponseBody
    public String allowedUserFindPW(@RequestBody final AllowedUserFindPWDTO allowedUserFindPWDTO) {
        return allowedUserService.allowedUserFindPW(allowedUserFindPWDTO);
    }

}
