package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Equipment.*;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;

import java.io.IOException;
import java.util.List;

public interface EquipmentService {
    boolean create(EquipmentCreateDTO equipmentCreateDTO) throws IOException; //운동기구 등록
    boolean update(EquipmentUpdateDTO equipmentUpdateDTO); //운동기구 수정
    boolean delete(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO); //운동기구 삭제
    List<Equipment> readAll(); //운동기구 전체 조회
    List<Equipment> readByCategory(EquipmentReadByCategoryDTO equipmentReadByCategoryDTO); //운동기구 카테고리별 조회
    List<EquipmentCategory> detailedRead(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO); //운동기구 상세조회
}
