package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Return.ReturnStringListDTO;
import capstone2021.smartGym_backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @CrossOrigin("*")
    @GetMapping("/reservation/calAvailableDate") //예약- 예약 가능일 계산
    @ResponseBody
    public ReturnStringListDTO calAvailableDate() {
        ReturnStringListDTO returnStringListDTO =new ReturnStringListDTO();
        returnStringListDTO.setData(reservationService.calAvailableDate());
        return returnStringListDTO;
    }
}
