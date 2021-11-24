package capstone2021.smartGym_backend.DTO.Return;

public class ReturnESLDetailedRead {
    private Long eslID;
    private String gymInfoName;
    private String userName;
    private String startTime;
    private String endTime;
    private String equipmentName;
    private String equipmentNameNth;
    private String equipmentQRCode;
    private int equipmentAvailable;

    public Long getEslID() {
        return eslID;
    }

    public void setEslID(Long eslID) {
        this.eslID = eslID;
    }

    public String getGymInfoName() {
        return gymInfoName;
    }

    public void setGymInfoName(String gymInfoName) {
        this.gymInfoName = gymInfoName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getEquipmentQRCode() {
        return equipmentQRCode;
    }

    public void setEquipmentQRCode(String equipmentQRCode) {
        this.equipmentQRCode = equipmentQRCode;
    }

    public int getEquipmentAvailable() {
        return equipmentAvailable;
    }

    public void setEquipmentAvailable(int equipmentAvailable) {
        this.equipmentAvailable = equipmentAvailable;
    }
}
