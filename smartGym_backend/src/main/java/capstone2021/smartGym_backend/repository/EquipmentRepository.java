package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;

import java.util.List;

public interface EquipmentRepository{
    boolean duplicateCheckEquipmentName(Long equipmentID, String equipmentName, String equipmentNameNth); //운동기구 이름 중복 체크
    void categorySettings(Equipment equipment, EquipmentCategory equipmentCategory); //운동기구 카테고리 DB에 값 삽입
    boolean create(Equipment equipment);
    boolean update(Equipment equipment);
    boolean delete(Equipment equipment);
    Equipment findByID(long id);
    List<Equipment> readAll();
    List<Equipment> readByCategory(String equipmentCategorySelect);
    List<EquipmentCategory> detailedRead(Equipment equipment);
}
