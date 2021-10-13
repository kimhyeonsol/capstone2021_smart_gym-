package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity //DB 테이블
public class Equipment {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    private Long equipment_id;

    @Column(length = 200) //크기
    @NotNull //널 허용 X
    private String equipment_name;

    @Column(length = 200)
    @NotNull
    private String equipment_category;

    @Column(length = 5000)
    @NotNull
    private String equipment_image;

    @Column(length = 8)
    @NotNull
    private int equipment_available;

    public Long getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipment_category() {
        return equipment_category;
    }

    public void setEquipment_category(String equipment_category) {
        this.equipment_category = equipment_category;
    }

    public String getEquipment_image() {
        return equipment_image;
    }

    public void setEquipment_image(String equipment_image) {
        this.equipment_image = equipment_image;
    }

    public int getEquipment_available() {
        return equipment_available;
    }

    public void setEquipment_available(int equipment_available) {
        this.equipment_available = equipment_available;
    }
}
