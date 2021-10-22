package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class GymHoliday {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    @Column(name="gymHoliday_id") //크기
    private Long gymHolidayID;

    @Column(name="gymHoliday_date", length = 20) //크기
    @NotNull //널 허용 X
    private String gymHolidayDate;
}
