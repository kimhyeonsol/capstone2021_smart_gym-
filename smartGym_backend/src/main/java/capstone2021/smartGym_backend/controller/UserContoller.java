package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Return.ReturnBooleanDTO;
import capstone2021.smartGym_backend.DTO.User.UserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserPhoneDuplDTO;
import capstone2021.smartGym_backend.service.AllowedUserService;
import capstone2021.smartGym_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserContoller {
    private final UserService userService;
    @Autowired
    public UserContoller(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }
    
    @CrossOrigin("*")
    @PostMapping("/user/phoneDuplicateCheck") //가입대기- 핸드폰 번호 중복 체크
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserPhoneDuplicateCheck(@RequestBody final UserPhoneDuplDTO userPhoneDuplDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(userPhoneDuplDTO.getUserPhone().equals(""))
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(userService.userPhoneDuplicateCheck(userPhoneDuplDTO));
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/user/emailDuplicateCheck") //가입대기- 이메일 중복 체크
    @ResponseBody
    public ReturnBooleanDTO unAllowedUserEmailDuplicateCheck(@RequestBody final UserEmailDuplDTO userEmailDuplDTO){
        ReturnBooleanDTO returnBooleanDTO=new ReturnBooleanDTO();
        if(userEmailDuplDTO.getUserEmail().equals(""))
            returnBooleanDTO.setSuccess(false);
        else {
            returnBooleanDTO.setData(userService.userEmailDuplicateCheck(userEmailDuplDTO));
        }
        return returnBooleanDTO;
    }

}
