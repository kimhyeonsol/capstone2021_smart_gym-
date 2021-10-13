package capstone2021.smartGym_backend.DTO;

public class EquipmentCreateDTO {
    private String equipmentName;
    private String equipmentCategory;
    private String equipmentImage;
    private int equipmentAvailable;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public void setEquipmentCategory(String equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }

    public String getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(String equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public int getEquipmentAvailable() {
        return equipmentAvailable;
    }

    public void setEquipmentAvailable(int equipmentAvailable) {
        this.equipmentAvailable = equipmentAvailable;
    }
}
