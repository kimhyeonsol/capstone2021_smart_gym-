package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.AllowedUser.*;
import capstone2021.smartGym_backend.DTO.Return.ReturnAllowedUserListDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnIntDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnStringDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserReadByIDDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserReadByNameDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.service.AllowedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AllowedUserController {

    private final AllowedUserService allowedUserService;
    @Autowired
    public AllowedUserController(AllowedUserService allowedUserService) {
        this.allowedUserService = allowedUserService;
    }

    @CrossOrigin("*")
    @PostMapping("/allowedUser/login") //가입승인- 로그인
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
    @PostMapping("/allowedUser/findID") //가입승인- 아이디찾기
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
    @PostMapping("/allowedUser/findPW") //가입승인- 비밀번호찾기
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

    @CrossOrigin("*")
    @GetMapping("/allowedUser/readAll") //가입승인- 사용자 전체 조회
    @ResponseBody
    public ReturnAllowedUserListDTO allowedUserReadAll() {
        ReturnAllowedUserListDTO returnAllowedUserListDTO=new ReturnAllowedUserListDTO();
        returnAllowedUserListDTO.setData(allowedUserService.allowedUserReadAll());
        return returnAllowedUserListDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/allowedUser/readByID") //가입승인- 사용자 ID 조회
    @ResponseBody
    public ReturnAllowedUserListDTO allowedUserReadByID(@RequestBody final AllowedUserReadByIDDTO allowedUserReadByIDDTO) {
        ReturnAllowedUserListDTO returnAllowedUserListDTO=new ReturnAllowedUserListDTO();
        if(allowedUserReadByIDDTO.getUserID().equals("")||allowedUserReadByIDDTO==null){
            returnAllowedUserListDTO.setSuccess(false);
        }
        else {
            returnAllowedUserListDTO.setData(allowedUserService.allowedUserReadByID(allowedUserReadByIDDTO));
        }
        return returnAllowedUserListDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/allowedUser/readByName") //가입승인- 사용자 이름 조회
    @ResponseBody
    public ReturnAllowedUserListDTO allowedUserReadByName(@RequestBody final AllowedUserReadByNameDTO allowedUserReadByNameDTO) {
        ReturnAllowedUserListDTO returnAllowedUserListDTO=new ReturnAllowedUserListDTO();
        if(allowedUserReadByNameDTO.getUserName().equals("")||allowedUserReadByNameDTO==null){
            returnAllowedUserListDTO.setSuccess(false);
        }
        else {
            returnAllowedUserListDTO.setData(allowedUserService.allowedUserReadByName(allowedUserReadByNameDTO));
        }
        return returnAllowedUserListDTO;

    }
}
