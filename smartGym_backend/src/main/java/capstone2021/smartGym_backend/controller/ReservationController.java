package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Return.ReturnBooleanDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @CrossOrigin("*")
    @GetMapping("/reservation/register") //가입대기- 회원가입
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
}
