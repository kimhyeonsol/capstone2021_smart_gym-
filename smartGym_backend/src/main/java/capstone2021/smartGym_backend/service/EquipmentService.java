package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Equipment.*;
import capstone2021.smartGym_backend.DTO.Return.ReturnEquipmentDetailedReadOnlyNameDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;

import java.io.IOException;
import java.util.List;

public interface EquipmentService {
    int create(EquipmentCreateDTO equipmentCreateDTO) throws IOException; //운동기구 등록
    int update(EquipmentUpdateDTO equipmentUpdateDTO) throws IOException; //운동기구 수정
    boolean delete(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO) throws IOException; //운동기구 삭제
    Equipment findByID(long id); //id로 운동기구 반환
    List<Equipment> readAll(EquipmentReadAllDTO equipmentReadAllDTO); //운동기구 전체 조회
    List<Equipment> readByCategory(EquipmentReadByCategoryDTO equipmentReadByCategoryDTO); //운동기구 카테고리별 조회
    List<EquipmentCategory> detailedRead(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO); //운동기구 상세조회
    ReturnEquipmentDetailedReadOnlyNameDTO detailedReadOnlyName(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO); //운동기구 상세조회(이름만)
}
