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
        EquipmentCreateDTO equipmentCreateDTO = new EquipmentCreateDTO();
        equipmentCreateDTO.setEquipmentName("equipment1");
        equipmentCreateDTO.setEquipmentCategory("어깨, 하체");
        equipmentCreateDTO.setEquipmentImage(" ");
        equipmentCreateDTO.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO);
    }

    @Test
    @Commit
    void update테스트() {
        EquipmentUpdateDTO equipmentUpdateDTO = new EquipmentUpdateDTO();
        equipmentUpdateDTO.setEquipmentID((long)1); //id 값 일치시켜야 업데이트 됨
        equipmentUpdateDTO.setEquipmentName("equipment1");
        equipmentUpdateDTO.setEquipmentCategory("이두, 삼두");
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
