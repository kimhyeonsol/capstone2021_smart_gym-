package capstone2021.smartGym_backend.DTO.GymInfo;

public class GymInfoDTO {
    private String gymInfoName;
    private String gymInfoAddress;
    private String gymInfoPhoneNumber;
    private String gymInfoEquipmentLayout;

    public String getGymInfoName() {
        return gymInfoName;
    }

    public void setGymInfoName(String gymInfoName) {
        this.gymInfoName = gymInfoName;
    }

    public String getGymInfoAddress() {
        return gymInfoAddress;
    }

    public void setGymInfoAddress(String gymInfoAddress) {
        this.gymInfoAddress = gymInfoAddress;
    }

    public String getGymInfoPhoneNumber() {
        return gymInfoPhoneNumber;
    }

    public void setGymInfoPhoneNumber(String gymInfoPhoneNumber) {
        this.gymInfoPhoneNumber = gymInfoPhoneNumber;
    }

    public String getGymInfoEquipmentLayout() {
        return gymInfoEquipmentLayout;
    }

    public void setGymInfoEquipmentLayout(String gymInfoEquipmentLayout) {
        this.gymInfoEquipmentLayout = gymInfoEquipmentLayout;
    }
}
