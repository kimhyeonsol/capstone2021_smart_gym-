package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;

import java.util.List;

public interface EquipmentRepository{
    boolean createEquipment(Equipment equipment);
    boolean updateEquipment(Equipment equipment);
    boolean deleteEquipment(Equipment equipment);
    List<Equipment> readAllEquipment();
    List<Equipment> readEquipment(String equipmentCategory);
    Equipment detailedReadEquipment(Equipment equipment);
}
