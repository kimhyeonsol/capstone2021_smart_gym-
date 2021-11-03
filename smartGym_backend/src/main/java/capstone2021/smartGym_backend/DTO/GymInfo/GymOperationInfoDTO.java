package capstone2021.smartGym_backend.DTO.GymInfo;

public class GymOperationInfoDTO {
    private String gymOperationInfoReservationDuration;
    private String gymOperationInfoRegularHoliday;
    private String gymOperationInfoOperatingStartTime;
    private String gymOperationInfoOperatingEndTime;

    public String getGymOperationInfoReservationDuration() {
        return gymOperationInfoReservationDuration;
    }

    public void setGymOperationInfoReservationDuration(String gymOperationInfoReservationDuration) {
        this.gymOperationInfoReservationDuration = gymOperationInfoReservationDuration;
    }

    public String getGymOperationInfoRegularHoliday() {
        return gymOperationInfoRegularHoliday;
    }

    public void setGymOperationInfoRegularHoliday(String gymOperationInfoRegularHoliday) {
        this.gymOperationInfoRegularHoliday = gymOperationInfoRegularHoliday;
    }

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
