package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Equipment.*;
import capstone2021.smartGym_backend.DTO.Return.ReturnEquipmentDetailedReadOnlyNameDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;
import capstone2021.smartGym_backend.service.ESLService;
import capstone2021.smartGym_backend.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final ESLService eslService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService,ESLService eslService) {
        this.equipmentService = equipmentService;
        this.eslService=eslService;
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/create") //운동기구 등록
    @ResponseBody
    public int equipmentCreate(@ModelAttribute final EquipmentCreateDTO equipmentCreateDTO) throws IOException {
        return equipmentService.create(equipmentCreateDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/update") //운동기구 수정
    @ResponseBody
    public int equipmentUpdate(@ModelAttribute final EquipmentUpdateDTO equipmentUpdateDTO, BindingResult bindingResult) throws IOException {
        int result=equipmentService.update(equipmentUpdateDTO);
        eslService.eslUpdateWhenUpdateEquipment(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentID());
        return result;
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/delete") //운동기구 삭제
    @ResponseBody
    public boolean equipmentDelete(@RequestBody final EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO) throws IOException {
        return equipmentService.delete(equipmentdetailedReadDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/readAll") //운동기구 전체 조회(정렬)
    @ResponseBody
    public List<Equipment> equipmentReadAll(@RequestBody final EquipmentReadAllDTO equipmentReadAllDTO){
        return equipmentService.readAll(equipmentReadAllDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/readByCategory") //운동기구 카테고리별 조회
    @ResponseBody
    public List<Equipment> equipmentReadByCategory(@RequestBody final EquipmentReadByCategoryDTO equipmentReadByCategoryDTO){
        return equipmentService.readByCategory(equipmentReadByCategoryDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/detailedRead") //운동기구 상세조회
    @ResponseBody
    public List<EquipmentCategory> equipmentDetailedRead(@RequestBody final EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO){
        return equipmentService.detailedRead(equipmentdetailedReadDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/detailedReadOnlyName") //운동기구 상세조회(이름+nth만)
    @ResponseBody
    public ReturnEquipmentDetailedReadOnlyNameDTO equipmentDetailedReadOnlyName(@RequestBody final EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO){
        return equipmentService.detailedReadOnlyName(equipmentdetailedReadDTO);
    }
}
