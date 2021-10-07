package capstone2021.smartGym_backend.service;


import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService{
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment create(Equipment equipment) {
        equipmentRepository.saveEquipment(equipment);

        return equipment;
    }

    @Override
    public Equipment update(Equipment equipment) {
        equipmentRepository.updateEquipment(equipment);

        return equipment;
    }

    @Override
    public Equipment delete(Equipment equipment) {
        equipmentRepository.deleteEquipment(equipment);

        return equipment;
    }
}
