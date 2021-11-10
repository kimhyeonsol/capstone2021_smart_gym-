package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Reservation.CalHolidayDateDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnDateListDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnEquipmentSearchByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Return.ReturnIntegerListDTO;
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

    @CrossOrigin("*")
    @GetMapping("/reservation/calRegularHolidayDate") //예약- 정기 휴무일 계산
    @ResponseBody
    public ReturnIntegerListDTO calRegularHolidayDate(@RequestBody CalHolidayDateDTO calHolidayDateDTO) {
        ReturnIntegerListDTO returnIntegerListDTO =new ReturnIntegerListDTO();
        if(calHolidayDateDTO==null){
            returnIntegerListDTO.setSuccess(false);
        }
        else {
            returnIntegerListDTO.setData(reservationService.calRegularHolidayDate(calHolidayDateDTO));
        }
        return returnIntegerListDTO;
    }

    @CrossOrigin("*")
    @GetMapping("/reservation/calHolidayDate") //예약- 휴무일 계산
    @ResponseBody
    public ReturnIntegerListDTO calHolidayDate(@RequestBody CalHolidayDateDTO calHolidayDateDTO) {
        ReturnIntegerListDTO returnIntegerListDTO =new ReturnIntegerListDTO();
        if(calHolidayDateDTO==null){
            returnIntegerListDTO.setSuccess(false);
        }
        else {
            returnIntegerListDTO.setData(reservationService.calHolidayDate(calHolidayDateDTO));
        }
        return returnIntegerListDTO;
    }

    @CrossOrigin("*")
    @GetMapping("/reservation/searchEquipmentByCategory") //운동기구 조회: 카테고리별 조회
    @ResponseBody
    public ReturnEquipmentSearchByCategoryDTO searchEquipmentByCategory() {
        ReturnEquipmentSearchByCategoryDTO returnEquipmentSearchByCategoryDTO =new ReturnEquipmentSearchByCategoryDTO();
        returnEquipmentSearchByCategoryDTO.setData(reservationService.searchEquipmentByCategory());
        return returnEquipmentSearchByCategoryDTO;
    }
}
