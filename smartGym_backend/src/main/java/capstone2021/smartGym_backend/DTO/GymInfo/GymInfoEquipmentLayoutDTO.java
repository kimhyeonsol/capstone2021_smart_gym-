package capstone2021.smartGym_backend.DTO.GymInfo;

import org.springframework.web.multipart.MultipartFile;

public class GymInfoEquipmentLayoutDTO {
    private MultipartFile gymInfoEquipmentLayout;

    public MultipartFile getGymInfoEquipmentLayout() {
        return gymInfoEquipmentLayout;
    }

    public void setGymInfoEquipmentLayout(MultipartFile gymInfoEquipmentLayout) {
        this.gymInfoEquipmentLayout = gymInfoEquipmentLayout;
    }
}
