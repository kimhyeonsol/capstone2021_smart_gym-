package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindIDDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindPWDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnIntDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnStringDTO;
import capstone2021.smartGym_backend.service.AllowedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AllowedUserController {

    AllowedUserService allowedUserService;
    @Autowired
    public AllowedUserController(AllowedUserService allowedUserService) {
        this.allowedUserService = allowedUserService;
    }

    @CrossOrigin("*")
    @GetMapping("/allowedUser/login") //가입승인- 로그인
    @ResponseBody
    public ReturnIntDTO allowedUserLogin(@RequestBody final AllowedUserLoginDTO allowedUserLoginDTO)  {
        ReturnIntDTO returnIntDTO =new ReturnIntDTO();
        if(allowedUserLoginDTO.getUserID().equals("")||allowedUserLoginDTO.getUserPW().equals("")){
            returnIntDTO.setSuccess(false);
        }
        else {
            returnIntDTO.setData(allowedUserService.allowedUserLogin(allowedUserLoginDTO));
        }
        return returnIntDTO;
    }

    @CrossOrigin("*")
    @GetMapping("/allowedUser/findID") //가입승인- 아이디찾기
    @ResponseBody
    public ReturnStringDTO allowedUserFindID(@RequestBody final AllowedUserFindIDDTO allowedUserFindIDDTO) {
        ReturnStringDTO resturnStringDTO =new ReturnStringDTO();
        String result=null;
        if(allowedUserFindIDDTO.getUserName().equals("")||allowedUserFindIDDTO.getUserEmail().equals("")){
            resturnStringDTO.setSuccess(false);
        }
        else {
            result=allowedUserService.allowedUserFindID(allowedUserFindIDDTO);
            if(result==null)
                resturnStringDTO.setCode(0);
            resturnStringDTO.setData(result);
        }
        return resturnStringDTO;

    }

    @CrossOrigin("*")
    @GetMapping("/allowedUser/findPW") //가입승인- 비밀번호찾기
    @ResponseBody
    public ReturnStringDTO allowedUserFindPW(@RequestBody final AllowedUserFindPWDTO allowedUserFindPWDTO) {
        ReturnStringDTO resturnStringDTO =new ReturnStringDTO();
        String result=null;
        if(allowedUserFindPWDTO.getUserID().equals("")||allowedUserFindPWDTO.getUserEmail().equals("")){
            resturnStringDTO.setSuccess(false);
        }
        else {
            result=allowedUserService.allowedUserFindPW(allowedUserFindPWDTO);
            if(result==null)
                resturnStringDTO.setCode(0);
            resturnStringDTO.setData(result);
        }
        return resturnStringDTO;
    }

}
