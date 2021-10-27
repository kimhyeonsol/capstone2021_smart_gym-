package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentUpdateDTO;
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
        /*EquipmentCreateDTO equipmentCreateDTO1 = new EquipmentCreateDTO();
        equipmentCreateDTO1.setEquipmentName("equipment");
        equipmentCreateDTO1.setEquipmentNameNth("1");
        equipmentCreateDTO1.setEquipmentCategoryList("가슴, 유산소");
        equipmentCreateDTO1.setEquipmentImage(" ");
        equipmentCreateDTO1.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO1);*/

        /*EquipmentCreateDTO equipmentCreateDTO2 = new EquipmentCreateDTO();
        equipmentCreateDTO2.setEquipmentName("equipment");
        equipmentCreateDTO2.setEquipmentNameNth("2");
        equipmentCreateDTO2.setEquipmentCategoryList("가슴, 유산소");
        equipmentCreateDTO2.setEquipmentImage(" ");
        equipmentCreateDTO2.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO2);*/

        /*EquipmentCreateDTO equipmentCreateDTO3 = new EquipmentCreateDTO();
        equipmentCreateDTO3.setEquipmentName("레그프레스");
        equipmentCreateDTO3.setEquipmentNameNth("1");
        equipmentCreateDTO3.setEquipmentCategoryList("하체");
        equipmentCreateDTO3.setEquipmentImage(" ");
        equipmentCreateDTO3.setEquipmentAvailable(2);
        equipmentService.create(equipmentCreateDTO3);*/

        /*EquipmentCreateDTO equipmentCreateDTO4 = new EquipmentCreateDTO();
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
        equipmentService.create(equipmentCreateDTO1);*/
    }

    @Test
    @Commit
    void update테스트() {
        EquipmentUpdateDTO equipmentUpdateDTO = new EquipmentUpdateDTO();
        equipmentUpdateDTO.setEquipmentID((long)2); //id 값 일치시켜야 업데이트 됨
        equipmentUpdateDTO.setEquipmentName("equipment");
        equipmentUpdateDTO.setEquipmentNameNth("2");
        equipmentUpdateDTO.setEquipmentCategoryList("가슴, 유산소");
        equipmentUpdateDTO.setEquipmentImage(" ");
        equipmentUpdateDTO.setEquipmentAvailable(2);
        equipmentService.update(equipmentUpdateDTO);
    }

    @Test
    @Commit
    void delete테스트() {
        EquipmentDeleteDetailedReadDTO equipmentDeleteDetailedReadDTO = new EquipmentDeleteDetailedReadDTO();
        equipmentDeleteDetailedReadDTO.setEquipmentID((long)4); //id 값만 일치하면 잘 삭제됨

        equipmentService.delete(equipmentDeleteDetailedReadDTO);
    }
}
