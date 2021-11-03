package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GymInfo {
    @Id //식별자
    @Column(name="gym_info_id", length = 11, columnDefinition = "int(11) default 1") //기본값 1
    private int gymInfoID = 1;

    @Column(name="gym_info_name", length = 200)
    @NotNull //널 허용 X
    private String gymInfoName;

    @Column(name="gym_info_address", length = 4000)
    @NotNull //널 허용 X
    private String gymInfoAddress;

    @Column(name="gym_info_phone_number", length = 20)
    @NotNull //널 허용 X
    private String gymInfoPhoneNumber;

    @Column(name="gym_info_equipment_layout", length = 5000, columnDefinition = "VARCHAR(5000) default ''")
    @NotNull //널 허용 X
    private String gymInfoEquipmentLayout = "";

    public int getGymInfoID() {
        return gymInfoID;
    }

    public void setGymInfoID(int gymInfoID) {
        this.gymInfoID = gymInfoID;
    }

    public String getGymInfoName() {
        return gymInfoName;
    }

    public void setGymInfoName(String gymInfoName) {
        this.gymInfoName = gymInfoName;
    }

    public String getGymInfoAddress() {
        return gymInfoAddress;
    }

    public void setGymInfoAddress(String gymInfoAddress) {
        this.gymInfoAddress = gymInfoAddress;
    }

    public String getGymInfoPhoneNumber() {
        return gymInfoPhoneNumber;
    }

    public void setGymInfoPhoneNumber(String gymInfoPhoneNumber) {
        this.gymInfoPhoneNumber = gymInfoPhoneNumber;
    }

    public String getGymInfoEquipmentLayout() {
        return gymInfoEquipmentLayout;
    }

    public void setGymInfoEquipmentLayout(String gymInfoEquipmentLayout) {
        this.gymInfoEquipmentLayout = gymInfoEquipmentLayout;
    }
}
