package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    @Column(name="reservation_id",length = 2000) //크기
    private Long reservationID;

    @ManyToOne
    @JoinColumn(name="user_id") //크기
    @NotNull //널 허용 X
    private AllowedUser userID;

    @ManyToOne
    @JoinColumn(name="equipment_id") //크기
    @NotNull //널 허용 X
    private Equipment equipmentID;

    @Column(name="start_time", length = 200) //크기
    @NotNull //널 허용 X
    private LocalDateTime startTime;

    @Column(name="end_time", length = 200) //크기
    @NotNull //널 허용 X
    private LocalDateTime endTime;


}
