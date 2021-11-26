package capstone2021.smartGym_backend.DTO.Return;

public class ReturnEquipmentDetailedReadOnlyNameDTO {
    private String equipmentName;
    private String equipmentNameNth;

    public ReturnEquipmentDetailedReadOnlyNameDTO(String equipmentName, String equipmentNameNth){
        this.equipmentName = equipmentName;
        this.equipmentNameNth = equipmentNameNth;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentNameNth() {
        return equipmentNameNth;
    }

    public void setEquipmentNameNth(String equipmentNameNth) {
        this.equipmentNameNth = equipmentNameNth;
    }
}
