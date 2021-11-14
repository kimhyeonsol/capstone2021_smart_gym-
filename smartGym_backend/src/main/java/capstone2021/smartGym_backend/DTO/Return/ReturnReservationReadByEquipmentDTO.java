package capstone2021.smartGym_backend.DTO.Return;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReturnReservationReadByEquipmentDTO {
    private Long reservationID;
    private String userID;
    private String equipmentName;
    private String equipmentNameNth;
    private String startTime;
    private String endTime;

    public ReturnReservationReadByEquipmentDTO(Long reservationID, String userID, String equipmentName, String equipmentNameNth, LocalDateTime startTime, LocalDateTime endTime){
        this.reservationID = reservationID;
        this.userID = userID;
        this.equipmentName = equipmentName;
        this.equipmentNameNth = equipmentNameNth;
        this.startTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.endTime = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
}
