package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Return.ReturnBooleanDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.*;
import capstone2021.smartGym_backend.DTO.User.UserIdDuplDTO;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UnAllowedUserController {
    private final UnAllowedUserService unAllowedUserService;

    @Autowired
    public UnAllowedUserController(UnAllowedUserService unAllowedUserService) {
        this.unAllowedUserService = unAllowedUserService;
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/register") //가입대기- 회원가입
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserRegister(@RequestBody final UnAllowedUserRegisterDTO unAllowedUserRegisterDTO) throws Exception {
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(unAllowedUserRegisterDTO.getUserID().equals(""))
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO));
        }
        return returnBooleanDTO;
    }




    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/approve") //가입대기- 가입대기자 승인
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserApprove(@RequestBody final UnAllowedUserApproveDTO unAllowedUserApproveDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(unAllowedUserApproveDTO.getUserID().equals(""))
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(unAllowedUserService.unAllowedUserApprove(unAllowedUserApproveDTO));
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/unApprove") //가입대기- 가입대기자 승인 거부
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserUnApprove(@RequestBody final UnAllowedUserApproveDTO unAllowedUserApproveDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(unAllowedUserApproveDTO.getUserID().equals("")||unAllowedUserApproveDTO==null)
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(unAllowedUserService.unAllowedUserUnApprove(unAllowedUserApproveDTO));
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @GetMapping("/unAllowedUser/readAll") //가입대기 사용자 전체 조회
    @ResponseBody
    public List<UnAllowedUser> unAllowedUserReadAll() {
        return unAllowedUserService.unAllowedUserReadAll();
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/readByID") //가입대기 사용자 ID 조회
    @ResponseBody
    public List<UnAllowedUser> unAllowedUserReadByID(@RequestBody final UnAllowedUserReadByIDDTO unAllowedUserReadByIDDTO) {
        return unAllowedUserService.unAllowedUserReadByID(unAllowedUserReadByIDDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/readByName") //가입대기 사용자 이름 조회
    @ResponseBody
    public List<UnAllowedUser> unAllowedUserReadByName(@RequestBody final UnAllowedUserReadByNameDTO unAllowedUserReadByNameDTO) {
        return unAllowedUserService.unAllowedUserReadByName(unAllowedUserReadByNameDTO);
    }

}
