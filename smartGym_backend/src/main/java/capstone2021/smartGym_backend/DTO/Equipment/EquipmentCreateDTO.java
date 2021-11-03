package capstone2021.smartGym_backend.DTO.Equipment;

import org.springframework.web.multipart.MultipartFile;

public class EquipmentCreateDTO {
    private MultipartFile equipmentImage;
    private EquipmentInfoCreateDTO equipmentInfoCreateDTO;

    public MultipartFile getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(MultipartFile equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public EquipmentInfoCreateDTO getEquipmentInfoCreateDTO() {
        return equipmentInfoCreateDTO;
    }

    public void setEquipmentInfoCreateDTO(EquipmentInfoCreateDTO equipmentInfoCreateDTO) {
        this.equipmentInfoCreateDTO = equipmentInfoCreateDTO;
    }
}
