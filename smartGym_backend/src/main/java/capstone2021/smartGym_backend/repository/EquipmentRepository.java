package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Return.ReturnEquipmentDetailedReadOnlyNameDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;

import java.util.List;

public interface EquipmentRepository{
    boolean duplicateCheckEquipmentName(Long equipmentID, String equipmentName, String equipmentNameNth); //운동기구 이름 중복 체크
    void categorySettings(Equipment equipment, EquipmentCategory equipmentCategory); //운동기구 카테고리 DB에 값 삽입
    int create(Equipment equipment);
    int update(Equipment equipment);
    boolean delete(Equipment equipment);
    boolean eslDelete(String id); //ESL 속성값 삭제
    int readOfEquipmentAvailable(Long equipmentID); //운동기구 상태 조회
    Equipment findByID(long id);
    List<Equipment> readAll(int select);
    List<Equipment> readByCategory(String equipmentCategorySelect);
    List<EquipmentCategory> detailedRead(Equipment equipment);
    ReturnEquipmentDetailedReadOnlyNameDTO detailedReadOnlyName(long id);
}
