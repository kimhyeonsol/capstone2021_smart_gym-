package capstone2021.smartGym_backend.DTO.ESL;

public class ESLDTO {

    Long ESLID;
    String equipmentName="";
    String equipmentNameNth="";
    String userName="";
    String reservationStartTime="";
    String reservationEndTime="";
    String gymInfoName="";
    String equipmentQRCode="";
    int equipmentAvailable;
    String csvFileName="";

    public Long getESLID() {
        return ESLID;
    }

    public void setESLID(Long ESLID) {
        this.ESLID = ESLID;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReservationStartTime() {
        return reservationStartTime;
    }

    public void setReservationStartTime(String reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
    }

    public String getReservationEndTime() {
        return reservationEndTime;
    }

    public void setReservationEndTime(String reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    public String getGymInfoName() {
        return gymInfoName;
    }

    public void setGymInfoName(String gymInfoName) {
        this.gymInfoName = gymInfoName;
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

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }
}
