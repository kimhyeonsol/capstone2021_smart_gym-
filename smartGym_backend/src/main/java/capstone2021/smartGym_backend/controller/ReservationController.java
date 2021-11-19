package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Reservation.*;
import capstone2021.smartGym_backend.DTO.Return.*;
import capstone2021.smartGym_backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/reservation/searchEquipmentByCategory") //예약 - 운동기구 조회: 카테고리별 조회
    @ResponseBody
    public ReturnEquipmentSearchByCategoryDTO searchEquipmentByCategory() {
        ReturnEquipmentSearchByCategoryDTO returnEquipmentSearchByCategoryDTO =new ReturnEquipmentSearchByCategoryDTO();
        returnEquipmentSearchByCategoryDTO.setData(reservationService.searchEquipmentByCategory());
        return returnEquipmentSearchByCategoryDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/makeReservation") //예약 - 운동기구 예약하기
    @ResponseBody
    public ReturnIntDTO makeReservation(@RequestBody ReservationCreateDTO reservationCreateDTO) {
        ReturnIntDTO returnIntDTO =new ReturnIntDTO();
        if(reservationCreateDTO==null){
            returnIntDTO.setSuccess(false);
        }
        else {
            returnIntDTO.setData(reservationService.makeReservation(reservationCreateDTO));
        }
        return returnIntDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/cancleReservation") //예약 - 운동기구 예약취소하기
    @ResponseBody
    public ReturnBooleanDTO cancleReservation(@RequestBody ReservationCancleDTO reservationCancleDTO) {
        ReturnBooleanDTO returnBooleanDTO =new ReturnBooleanDTO();
        if(reservationCancleDTO==null){
            returnBooleanDTO.setSuccess(false);
        }
        else {
            returnBooleanDTO.setData(reservationService.cancleReservation(reservationCancleDTO));
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/readByEquipment") //운동기구 별 예약 조회
    @ResponseBody
    public List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(@RequestBody ReservationReadByEquipmentDTO reservationReadByEquipmentDTO){
        return reservationService.reservationReadByEquipment(reservationReadByEquipmentDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/readMyReservationOfSelectedDay") //예약 - 선택한날짜 내 예약 조회
    @ResponseBody
    public ReturnReservationListDTO readMyReservationOfSelectedDay(@RequestBody ReservationReadSelectedDayDTO reservationReadSelectedDayDTO) {
        ReturnReservationListDTO returnReservationListDTO =new ReturnReservationListDTO();
        if(reservationReadSelectedDayDTO==null){
            returnReservationListDTO.setSuccess(false);
        }
        else {
            returnReservationListDTO.setData(reservationService.readMyReservationOfSelectedDay(reservationReadSelectedDayDTO));
        }
        return returnReservationListDTO;
    }
}
