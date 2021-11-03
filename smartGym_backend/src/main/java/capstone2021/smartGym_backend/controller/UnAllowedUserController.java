package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Return.ReturnBooleanDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserIdDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserPhoneDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @PostMapping("/unAllowedUser/idDuplicateCheck") //가입대기- id 중복 체크
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserIdDuplicateCheck(@RequestBody final UnAllowedUserIdDuplDTO unAllowedUserIdDuplDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(unAllowedUserIdDuplDTO.getUserID().equals("")) {
            returnBooleanDTO.setSuccess(false);
        }
        else {
            returnBooleanDTO.setData(unAllowedUserService.unAllowedUserIdDuplicateCheck(unAllowedUserIdDuplDTO));
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/phoneDuplicateCheck") //가입대기- 핸드폰 번호 중복 체크
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserPhoneDuplicateCheck(@RequestBody final UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(unAllowedUserPhoneDuplDTO.getUserPhone().equals(""))
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(unAllowedUserService.unAllowedUserPhoneDuplicateCheck(unAllowedUserPhoneDuplDTO));
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/emailDuplicateCheck") //가입대기- 이메일 중복 체크
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserEmailDuplicateCheck(@RequestBody final UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(unAllowedUserEmailDuplDTO.getUserEmail().equals(""))
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(unAllowedUserService.unAllowedUserEmailDuplicateCheck(unAllowedUserEmailDuplDTO));
        }
        return returnBooleanDTO;
    }


}
