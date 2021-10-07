package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.repository.EquipmentRepository;
import capstone2021.smartGym_backend.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EquipmentApplicationTests extends SmartGymBackendApplicationTests{
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    EquipmentRepository equipmentRepository;

    @Test
    @Commit
    void create테스트() {
        Equipment equipment = new Equipment();
        equipment.setEquipment_name("legmachine_22");
        equipment.setEquipment_category("leg");
        equipment.setEquipment_image(" ");
        equipment.setEquipment_available(2);
        equipmentService.create(equipment);
    }

    @Test
    @Commit
    void update테스트() {
        Equipment equipment = new Equipment();
        equipment.setEquipment_id((long)3); //id 값 일치시켜야 업데이트 됨
        equipment.setEquipment_name("legmachine_3");
        equipment.setEquipment_category("leg");
        equipment.setEquipment_image(" ");
        equipment.setEquipment_available(2);
        equipmentService.update(equipment);
    }

    @Test
    @Commit
    void delete테스트() {
        Equipment equipment = new Equipment();
        equipment.setEquipment_id((long)4); //id 값만 일치하면 잘 삭제됨
        equipment.setEquipment_name("legmachine_1");
        equipment.setEquipment_category("leg");
        equipment.setEquipment_image(" ");
        equipment.setEquipment_available(2);
        equipmentService.delete(equipment);
    }
}
