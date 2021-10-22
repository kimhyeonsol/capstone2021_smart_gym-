package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GymOperationInfo {
    @Id //식별자
    @Column(name="gymOperationInfo_id", length = 11) //크기
    private int gymOperationInfoID;

    @Column(name="gymOperationInfo_reservationDuration", length = 8) //크기
    @NotNull //널 허용 X
    private String gymOperationInfoReservationDuration;

    @Column(name="gymOperationInfo_regularHoliday", length = 20) //크기
    @NotNull //널 허용 X
    private String gymOperationInfoRegularHoliday;

    @Column(name="gymOperationInfo_operatingTime", length = 200) //크기
    @NotNull //널 허용 X
    private String gymOperationInfoOperatingTime;
}
