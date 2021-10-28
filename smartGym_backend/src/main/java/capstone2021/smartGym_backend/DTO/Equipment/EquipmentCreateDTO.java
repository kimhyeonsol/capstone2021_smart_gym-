package capstone2021.smartGym_backend.DTO.Equipment;

import org.springframework.web.multipart.MultipartFile;

public class EquipmentCreateDTO {
    private MultipartFile equipmentImage;
    private EquipmentInfoCreateDTO equipmentInfoDTO;

    public MultipartFile getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(MultipartFile equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public EquipmentInfoCreateDTO getEquipmentInfoDTO() {
        return equipmentInfoDTO;
    }

    public void setEquipmentInfoDTO(EquipmentInfoCreateDTO equipmentInfoDTO) {
        this.equipmentInfoDTO = equipmentInfoDTO;
    }
}
