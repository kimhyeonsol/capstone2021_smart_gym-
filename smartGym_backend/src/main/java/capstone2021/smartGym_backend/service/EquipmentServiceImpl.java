package capstone2021.smartGym_backend.service;


import capstone2021.smartGym_backend.DTO.*;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService{
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public boolean create(EquipmentCreateDTO equipmentCreateDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentCreateDTO.getEquipmentName());
        equipment.setEquipmentCategory(equipmentCreateDTO.getEquipmentCategory());
        equipment.setEquipmentImage(equipmentCreateDTO.getEquipmentImage());
        equipment.setEquipmentAvailable(equipmentCreateDTO.getEquipmentAvailable());

        return equipmentRepository.createEquipment(equipment);
    }

    @Override
    public boolean update(EquipmentUpdateDTO equipmentUpdateDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentUpdateDTO.getEquipmentID());
        equipment.setEquipmentName(equipmentUpdateDTO.getEquipmentName());
        equipment.setEquipmentCategory(equipmentUpdateDTO.getEquipmentCategory());
        equipment.setEquipmentImage(equipmentUpdateDTO.getEquipmentImage());
        equipment.setEquipmentAvailable(equipmentUpdateDTO.getEquipmentAvailable());

        return equipmentRepository.updateEquipment(equipment);
    }

    @Override
    public boolean delete(EquipmentDeleteDetailedReadDTO equipmentDeleteDetailedReadDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentDeleteDetailedReadDTO.getEquipmentID());

        return equipmentRepository.deleteEquipment(equipment);
    }

    @Override
    public List<Equipment> readAll() {
        return equipmentRepository.readAllEquipment();
    }

    @Override
    public List<Equipment> read(EquipmentReadDTO equipmentReadDTO){
        String equipmentCategory = equipmentReadDTO.getEquipmentCategory();

        return equipmentRepository.readEquipment(equipmentCategory);
    }

    @Override
    public Equipment detailedRead(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentdetailedReadDTO.getEquipmentID());

        return equipmentRepository.detailedReadEquipment(equipment);
    }
}
