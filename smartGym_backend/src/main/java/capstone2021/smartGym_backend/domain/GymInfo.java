package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class GymInfo {
    @Id //식별자
    @Column(name="gym_info_id", length = 11) //크기
    private int gymInfoID;

    @Column(name="gym_info_name", length = 200) //크기
    @NotNull //널 허용 X
    private String gymInfoName;

    @Column(name="gym_info_address", length = 4000) //크기
    @NotNull //널 허용 X
    private String gymInfoAddress;

    @Column(name="gym_info_phone_number", length = 20) //크기
    @NotNull //널 허용 X
    private String gymInfoPhoneNumber;

    @Column(name="gym_info_equipment_layout", length = 5000) //크기
    @NotNull //널 허용 X
    private String gymInfoEquipmentLayout;
}
