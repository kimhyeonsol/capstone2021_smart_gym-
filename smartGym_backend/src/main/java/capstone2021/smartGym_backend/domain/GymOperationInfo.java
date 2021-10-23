package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GymOperationInfo {
    @Id //식별자
    @Column(name="gym_operation_info_id", length = 11) //크기
    private int gymOperationInfoID;

    @Column(name="gym_operation_info_reservation_duration", length = 8) //크기
    @NotNull //널 허용 X
    private String gymOperationInfoReservationDuration;

    @Column(name="gym_operation_info_regular_holiday", length = 20) //크기
    @NotNull //널 허용 X
    private String gymOperationInfoRegularHoliday;

    @Column(name="gym_operation_info_operating_time", length = 200) //크기
    @NotNull //널 허용 X
    private String gymOperationInfoOperatingTime;
}
