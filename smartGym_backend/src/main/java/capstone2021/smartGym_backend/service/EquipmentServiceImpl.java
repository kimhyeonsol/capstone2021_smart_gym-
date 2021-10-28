package capstone2021.smartGym_backend.service;


import capstone2021.smartGym_backend.DTO.Equipment.*;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;
import capstone2021.smartGym_backend.repository.EquipmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl extends ImageService implements EquipmentService{
    private static final java.util.UUID UUID = null;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @SneakyThrows
    @Override
    public boolean create(EquipmentCreateDTO equipmentCreateDTO){
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentCreateDTO.getEquipmentInfoDTO().getEquipmentName());
        equipment.setEquipmentNameNth(equipmentCreateDTO.getEquipmentInfoDTO().getEquipmentNameNth());
        equipment.setEquipmentCategoryList(equipmentCreateDTO.getEquipmentInfoDTO().getEquipmentCategoryList());
        equipment.setEquipmentAvailable(equipmentCreateDTO.getEquipmentInfoDTO().getEquipmentAvailable());

        String fileName = UUID.randomUUID() + "_" + equipmentCreateDTO.getEquipmentImage().getOriginalFilename();
        String fileUrl = upload(equipmentCreateDTO.getEquipmentImage(), fileName,  "/");
        equipment.setEquipmentImage(fileUrl);

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
