package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Reservation.*;
import capstone2021.smartGym_backend.DTO.Return.*;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.service.ESLService;
import capstone2021.smartGym_backend.service.EquipmentService;
import capstone2021.smartGym_backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final ESLService eslService;
    private final EquipmentService equipmentService;
    @Autowired
    public ReservationController(ReservationService reservationService, ESLService eslService, EquipmentService equipmentService) {
        this.reservationService = reservationService;
        this.eslService=eslService;
        this.equipmentService=equipmentService;
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
    public ReturnIntegerListDTO calRegularHolidayDate(@RequestParam( value="year",required = false)int year,@RequestParam( value="month",required = false)int month) {
        ReturnIntegerListDTO returnIntegerListDTO =new ReturnIntegerListDTO();
        CalHolidayDateDTO calHolidayDateDTO=new CalHolidayDateDTO();

        calHolidayDateDTO.setYear(year);
        calHolidayDateDTO.setMonth(month);

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
    public ReturnIntegerListDTO calHolidayDate(@RequestParam( value="year",required = false)int year,@RequestParam( value="month",required = false)int month) {
        ReturnIntegerListDTO returnIntegerListDTO =new ReturnIntegerListDTO();

        CalHolidayDateDTO calHolidayDateDTO=new CalHolidayDateDTO();

        calHolidayDateDTO.setYear(year);
        calHolidayDateDTO.setMonth(month);
        
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
        Equipment equipment;
        Long equipmentID;
        String oldStart;
        if(reservationCreateDTO==null){
            returnIntDTO.setSuccess(false);
        }
        else {
            equipment=equipmentService.findByID(reservationCreateDTO.getEquipmentID());
            oldStart=eslService.recentReservation(equipment);
            returnIntDTO.setData(reservationService.makeReservation(reservationCreateDTO));
            if(!eslService.recentReservation(equipment).equals(oldStart))
                eslService.eslUpdateWhenCancleReservation(equipment.getEquipmentID());
        }
        return returnIntDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/cancleReservation") //예약 - 운동기구 예약취소하기
    @ResponseBody
    public ReturnBooleanDTO cancleReservation(@RequestBody ReservationCancleDTO reservationCancleDTO) {
        ReturnBooleanDTO returnBooleanDTO =new ReturnBooleanDTO();
        Equipment equipment;
        Long equipmentID;
        String oldStart;
        if(reservationCancleDTO==null){
            returnBooleanDTO.setSuccess(false);
        }
        else {
            equipment=reservationService.findByID(reservationCancleDTO.getReservationID()).getEquipmentID();
            oldStart=eslService.recentReservation(equipment);
            returnBooleanDTO.setData(reservationService.cancleReservation(reservationCancleDTO));
            if(!eslService.recentReservation(equipment).equals(oldStart))
                eslService.eslUpdateWhenCancleReservation(equipment.getEquipmentID());
        }
        return returnBooleanDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/terminateReservation") //예약 - 운동기구 예약종료하기
    @ResponseBody
    public ReturnBooleanDTO terminateReservation(@RequestBody ReservationCancleDTO reservationCancleDTO) {
        ReturnBooleanDTO returnBooleanDTO =new ReturnBooleanDTO();
        if(reservationCancleDTO==null){
            returnBooleanDTO.setSuccess(false);
        }
        else {
            returnBooleanDTO.setData(reservationService.terminateReservation(reservationCancleDTO));
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

    @CrossOrigin("*")
    @PostMapping("/reservation/readEquipmentReservationOfSeletedDay") //예약 - 선택한날짜 운동기구별 예약 조회
    @ResponseBody
    public ReturnReservationListDTO readEquipmentReservationOfSeletedDay(@RequestBody ReservationReadBySelectedDayAndEquipmentDTO reservationReadBySelectedDayAndEquipmentDTO) {
        ReturnReservationListDTO returnReservationListDTO =new ReturnReservationListDTO();
        if(reservationReadBySelectedDayAndEquipmentDTO==null){
            returnReservationListDTO.setSuccess(false);
        }
        else {
            returnReservationListDTO.setData(reservationService.readEquipmentReservationOfSeletedDay(reservationReadBySelectedDayAndEquipmentDTO));
        }
        return returnReservationListDTO;
    }

    @CrossOrigin("*")
    @GetMapping("/reservation/readOperatingTime") //예약 - 운영 시작시간, 마감시간 조회
    @ResponseBody
    public ReturnOperationTimeDTO readOperatingTime() {
        ReturnOperationTimeDTO returnOperationTimeDTO =new ReturnOperationTimeDTO();
        returnOperationTimeDTO.setData(reservationService.readOperatingTime());
        return returnOperationTimeDTO;
    }

    @CrossOrigin("*")
    @PostMapping("/reservation/isInUse") //예약 - 운영 시작시간, 마감시간 조회
    @ResponseBody
    public Boolean isInUse(@RequestBody IsInUseDTO isInUseDTO) {
        List<Reservation>list= reservationService.equipmentIsinUseCurrently(isInUseDTO.getEquipmentID());

        if(list.isEmpty())
            return false;
        else
            return true;
    }

}
