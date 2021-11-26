package capstone2021.smartGym_backend.DTO.ESL;

public class ESLEquipmentMatchingDTO {
    private String eslID;
    private Long equipmentID;

    public String getEslID() {
        return eslID;
    }

    public void setEslID(String eslID) {
        this.eslID = eslID;
    }

    public Long getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Long equipmentID) {
        this.equipmentID = equipmentID;
    }
}
