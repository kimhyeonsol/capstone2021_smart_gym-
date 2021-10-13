package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EquipmentRepository{
    Equipment saveEquipment(Equipment equipment);
    Equipment updateEquipment(Equipment equipment);
    Equipment deleteEquipment(Equipment equipment);
}
