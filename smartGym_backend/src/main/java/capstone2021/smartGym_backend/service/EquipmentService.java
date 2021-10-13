package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.domain.Equipment;

public interface EquipmentService {
    Equipment create(Equipment equipment);
    Equipment update(Equipment equipment);
    Equipment delete(Equipment equipment);
}
