package capstone2021.smartGym_backend.DTO.Equipment;

import org.springframework.web.multipart.MultipartFile;

public class EquipmentUpdateDTO {
    private MultipartFile equipmentImage;
    private EquipmentInfoUpdateDTO equipmentInfoUpdateDTO;

    public MultipartFile getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(MultipartFile equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public EquipmentInfoUpdateDTO getEquipmentInfoUpdateDTO() {
        return equipmentInfoUpdateDTO;
    }

    public void setEquipmentInfoUpdateDTO(EquipmentInfoUpdateDTO equipmentInfoUpdateDTO) {
        this.equipmentInfoUpdateDTO = equipmentInfoUpdateDTO;
    }
}
