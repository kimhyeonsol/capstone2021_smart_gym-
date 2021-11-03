package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class GymOperationInfo {
    @Id //식별자
    @Column(name="gym_operation_info_id", length = 11, columnDefinition = "int(11) default 1") //기본값 1
    private int gymOperationInfoID = 1;

    @Column(name="gym_operation_info_reservation_duration", length = 8, columnDefinition = "VARCHAR(11) default '7'")
    @NotNull //널 허용 X
    private String gymOperationInfoReservationDuration = "7";

    @Column(name="gym_operation_info_regular_holiday", length = 20)
    @NotNull //널 허용 X
    private String gymOperationInfoRegularHoliday;

    @Column(name="gym_operation_info_operating_start_time", length = 6, columnDefinition = "TIME(6) default '00:00:00'")
    @NotNull //널 허용 X
    @Temporal(TemporalType.TIME) //TIME 형식
    private Date gymOperationInfoOperatingStartTime;

    @Column(name="gym_operation_info_operating_end_time", length = 6, columnDefinition = "TIME(6) default '23:59:00'")
    @NotNull //널 허용 X
    @Temporal(TemporalType.TIME) //TIME 형식
    private Date gymOperationInfoOperatingEndTime;

    public GymOperationInfo(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            gymOperationInfoOperatingStartTime = format.parse("00:00:00");
            gymOperationInfoOperatingEndTime = format.parse("23:59:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getGymOperationInfoID() {
        return gymOperationInfoID;
    }

    public void setGymOperationInfoID(int gymOperationInfoID) {
        this.gymOperationInfoID = gymOperationInfoID;
    }

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

    public Date getGymOperationInfoOperatingStartTime() {
        return gymOperationInfoOperatingStartTime;
    }

    public void setGymOperationInfoOperatingStartTime(Date gymOperationInfoOperatingStartTime) {
        this.gymOperationInfoOperatingStartTime = gymOperationInfoOperatingStartTime;
    }

    public Date getGymOperationInfoOperatingEndTime() {
        return gymOperationInfoOperatingEndTime;
    }

    public void setGymOperationInfoOperatingEndTime(Date gymOperationInfoOperatingEndTime) {
        this.gymOperationInfoOperatingEndTime = gymOperationInfoOperatingEndTime;
    }
}
