package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.DTO.EquipmentCreateDTO;
import capstone2021.smartGym_backend.DTO.EquipmentDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.EquipmentUpdateDTO;
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
        EquipmentCreateDTO equipmentCreateDTO1 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment1");
        equipmentCreateDTO1.setEquipmentCategory("011000000100");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);

        EquipmentCreateDTO equipmentCreateDTO2 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment2");
        equipmentCreateDTO1.setEquipmentCategory("100000000000");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);

        EquipmentCreateDTO equipmentCreateDTO3 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment3");
        equipmentCreateDTO1.setEquipmentCategory("000000010001");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);

        EquipmentCreateDTO equipmentCreateDTO4 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment4");
        equipmentCreateDTO1.setEquipmentCategory("000000100000");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);

        EquipmentCreateDTO equipmentCreateDTO5 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment5");
        equipmentCreateDTO1.setEquipmentCategory("010000000100");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);

        EquipmentCreateDTO equipmentCreateDTO6 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment6");
        equipmentCreateDTO1.setEquipmentCategory("001000101000");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);
    }

    @Test
    @Commit
    void update테스트() {
        EquipmentUpdateDTO equipmentUpdateDTO = new EquipmentUpdateDTO();
        equipmentUpdateDTO.setEquipmentID((long)1); //id 값 일치시켜야 업데이트 됨
        equipmentUpdateDTO.setEquipmentName("equipment1");
        equipmentUpdateDTO.setEquipmentCategory("001000000100");
        equipmentUpdateDTO.setEquipmentImage(" ");
        equipmentUpdateDTO.setEquipmentAvailable(2);
        equipmentService.update(equipmentUpdateDTO);
    }

    @Test
    @Commit
    void delete테스트() {
        EquipmentDeleteDetailedReadDTO equipmentDeleteDetailedReadDTO = new EquipmentDeleteDetailedReadDTO();
        equipmentDeleteDetailedReadDTO.setEquipmentID((long)1); //id 값만 일치하면 잘 삭제됨

        equipmentService.delete(equipmentDeleteDetailedReadDTO);
    }
}
