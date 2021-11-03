package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class ESL {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    @Column(name="ESL_id") //크기
    private Long ESLID;

    @OneToOne
    @JoinColumn(name="equipment_id") //크기
    @NotNull //널 허용 X
    private Equipment equipmentID;

    @OneToOne
    @JoinColumn(name="reservation_id") //크기
    @NotNull //널 허용 X
    private Reservation reservationID;

    @OneToOne
    @JoinColumn(name="gymInfo_id") //크기
    @NotNull //널 허용 X
    private GymInfo gymInfoID;
}
