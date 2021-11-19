package capstone2021.smartGym_backend.DTO.Reservation;

public class ReservationReadOperatingTimeDTO {
    private String gymOperationInfoOperatingStartTime;
    private String gymOperationInfoOperatingEndTime;

    public String getGymOperationInfoOperatingStartTime() {
        return gymOperationInfoOperatingStartTime;
    }

    public void setGymOperationInfoOperatingStartTime(String gymOperationInfoOperatingStartTime) {
        this.gymOperationInfoOperatingStartTime = gymOperationInfoOperatingStartTime;
    }

    public String getGymOperationInfoOperatingEndTime() {
        return gymOperationInfoOperatingEndTime;
    }

    public void setGymOperationInfoOperatingEndTime(String gymOperationInfoOperatingEndTime) {
        this.gymOperationInfoOperatingEndTime = gymOperationInfoOperatingEndTime;
    }
}
