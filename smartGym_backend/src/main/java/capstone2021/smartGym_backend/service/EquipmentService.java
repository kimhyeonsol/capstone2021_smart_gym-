package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.*;
import capstone2021.smartGym_backend.domain.Equipment;

import java.util.List;

public interface EquipmentService {
    boolean create(EquipmentCreateDTO equipmentCreateDTO);
    boolean update(EquipmentUpdateDTO equipmentUpdateDTO);
    boolean delete(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO);
    List<Equipment> readAll();
    List<Equipment> read(EquipmentReadDTO equipmentReadDTO);
    Equipment detailedRead(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO);
}
