package capstone2021.smartGym_backend.service;


import capstone2021.smartGym_backend.DTO.Equipment.EquipmentCreateDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentReadByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentUpdateDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;
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
        equipment.setEquipmentNameNth(equipmentCreateDTO.getEquipmentNameNth());
        equipment.setEquipmentCategoryList(equipmentCreateDTO.getEquipmentCategoryList());
        equipment.setEquipmentImage(equipmentCreateDTO.getEquipmentImage());
        equipment.setEquipmentAvailable(equipmentCreateDTO.getEquipmentAvailable());

        return equipmentRepository.create(equipment);
    }

    @Override
    public boolean update(EquipmentUpdateDTO equipmentUpdateDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentUpdateDTO.getEquipmentID());
        equipment.setEquipmentName(equipmentUpdateDTO.getEquipmentName());
        equipment.setEquipmentNameNth(equipmentUpdateDTO.getEquipmentNameNth());
        equipment.setEquipmentCategoryList(equipmentUpdateDTO.getEquipmentCategoryList());
        equipment.setEquipmentImage(equipmentUpdateDTO.getEquipmentImage());
        equipment.setEquipmentAvailable(equipmentUpdateDTO.getEquipmentAvailable());

        return equipmentRepository.update(equipment);
    }

    @Override
    public boolean delete(EquipmentDeleteDetailedReadDTO equipmentDeleteDetailedReadDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentDeleteDetailedReadDTO.getEquipmentID());

        return equipmentRepository.delete(equipment);
    }

    @Override
    public List<Equipment> readAll() {
        return equipmentRepository.readAll();
    }

    @Override
    public List<Equipment> readByCategory(EquipmentReadByCategoryDTO equipmentReadByCategoryDTO){
        String equipmentCategorySelect = equipmentReadByCategoryDTO.getEquipmentCategorySelect();

        return equipmentRepository.readByCategory(equipmentCategorySelect);
    }

    @Override
    public List<EquipmentCategory> detailedRead(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentdetailedReadDTO.getEquipmentID());

        return equipmentRepository.detailedRead(equipment);
    }
}
