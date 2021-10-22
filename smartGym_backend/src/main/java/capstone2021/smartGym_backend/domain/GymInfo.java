package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class GymInfo {
    @Id //식별자
    @Column(name="gymInfo_id", length = 11) //크기
    private int gymInfoID;

    @Column(name="gymInfo_name", length = 200) //크기
    @NotNull //널 허용 X
    private String gymInfoName;

    @Column(name="gymInfo_address", length = 4000) //크기
    @NotNull //널 허용 X
    private String gymInfoAddress;

    @Column(name="gymInfo_phoneNumber", length = 20) //크기
    @NotNull //널 허용 X
    private String gymInfoPhoneNumber;

    @Column(name="gymInfo_equipmentLayout", length = 5000) //크기
    @NotNull //널 허용 X
    private String gymInfoEquipmentLayout;
}
