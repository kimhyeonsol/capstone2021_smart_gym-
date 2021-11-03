package capstone2021.smartGym_backend.controller;

import capstone2021.smartGym_backend.DTO.Equipment.*;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;
import capstone2021.smartGym_backend.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class EquipmentController {
    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/create") //운동기구 등록
    @ResponseBody
    public boolean equipmentCreate(@ModelAttribute final EquipmentCreateDTO equipmentCreateDTO) throws IOException {
        return equipmentService.create(equipmentCreateDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/update") //운동기구 수정 
    @ResponseBody
    public boolean equipmentUpdate(@ModelAttribute final EquipmentUpdateDTO equipmentUpdateDTO) throws IOException {
        return equipmentService.update(equipmentUpdateDTO);
    }

    @CrossOrigin("*")
    @PostMapping("/equipment/delete") //운동기구 삭제
    @ResponseBody
    public boolean equipmentDelete(@RequestBody final EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO) throws IOException {
        return equipmentService.delete(equipmentdetailedReadDTO);
    }

    @CrossOrigin("*")
    @GetMapping("/equipment/readAll") //운동기구 전체 조회
    @ResponseBody
    public List<Equipment> equipmentReadAll(){
        return equipmentService.readAll();
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
}
