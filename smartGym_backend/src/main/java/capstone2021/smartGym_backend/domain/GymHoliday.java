package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class GymHoliday {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    @Column(name="gym_holiday_id")
    private Long gymHolidayID;

    @Column(name="gym_holiday_date", length = 8)
    @NotNull //널 허용 X
    @Temporal(TemporalType.DATE) //DATE 형식
    private Date gymHolidayDate;

    public GymHoliday(){
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        try {
            gymHolidayDate = format.parse("0000-00-00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Long getGymHolidayID() {
        return gymHolidayID;
    }

    public void setGymHolidayID(Long gymHolidayID) {
        this.gymHolidayID = gymHolidayID;
    }

    public Date getGymHolidayDate() {
        return gymHolidayDate;
    }

    public void setGymHolidayDate(Date gymHolidayDate) {
        this.gymHolidayDate = gymHolidayDate;
    }
}
