package capstone2021.smartGym_backend.controller;

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
    public boolean unAllowedUserRegister(@RequestBody final UnAllowedUserRegisterDTO unAllowedUserRegisterDTO) throws Exception {
        return unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/idDuplicateCheck") //가입대기- id 중복 체크
    @ResponseBody
    public boolean unAllowedUserIdDuplicateCheck(@RequestBody final UnAllowedUserIdDuplDTO unAllowedUserDuplDTO){
        return unAllowedUserService.unAllowedUserIdDuplicateCheck(unAllowedUserDuplDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/phoneDuplicateCheck") //가입대기- 핸드폰 번호 중복 체크
    @ResponseBody
    public boolean unAllowedUserPhoneDuplicateCheck(@RequestBody final UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO){
        return unAllowedUserService.unAllowedUserPhoneDuplicateCheck(unAllowedUserPhoneDuplDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/unAllowedUser/emailDuplicateCheck") //가입대기- 이메일 중복 체크
    @ResponseBody
    public boolean unAllowedUserEmailDuplicateCheck(@RequestBody final UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO){
        return unAllowedUserService.unAllowedUserEmailDuplicateCheck(unAllowedUserEmailDuplDTO);
    }


}
