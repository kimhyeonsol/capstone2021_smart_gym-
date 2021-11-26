package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity //DB 테이블
public class Equipment {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="equipment_id")
    private Long equipmentID;

    @Column(name="equipment_name", length = 200) //운동기구 이름
    @NotNull //널 허용 X
    private String equipmentName;

    @Column(name="equipment_name_nth", length = 200) //운동기구 이름 순서
    @NotNull
    private String equipmentNameNth;

    @Column(name="equipment_category_list", length = 200) //운동기구 카테고리 문자열
    @NotNull
    private String equipmentCategoryList;

    @Column(name="equipment_image", length = 5000) //운동기구 이미지
    @NotNull
    private String equipmentImage;

    @Column(name="equipment_available", length = 11) //운동기구 상태
    @NotNull
    private int equipmentAvailable;

    @Column(name="equipment_qr_code", length = 5000) //운동기구 QR 코드 URL
    @NotNull
    private String equipmentQRCode;

    @Column(name="esl_id", length = 200) //ESL 아이디
    private String eslID;


    public Long getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Long equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentNameNth() {
        return equipmentNameNth;
    }

    public void setEquipmentNameNth(String equipmentNameNth) {
        this.equipmentNameNth = equipmentNameNth;
    }

    public String getEquipmentCategoryList() {
        return equipmentCategoryList;
    }

    public void setEquipmentCategoryList(String equipmentCategoryList) {
        this.equipmentCategoryList = equipmentCategoryList;
    }

    public String getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(String equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public int getEquipmentAvailable() {
        return equipmentAvailable;
    }

    public void setEquipmentAvailable(int equipmentAvailable) {
        this.equipmentAvailable = equipmentAvailable;
    }

    public String getEquipmentQRCode() {
        return equipmentQRCode;
    }

    public void setEquipmentQRCode(String equipmentQRCode) {
        this.equipmentQRCode = equipmentQRCode;
    }

    public String getEslID() {
        return eslID;
    }

    public void setEslID(String eslID) {
        this.eslID = eslID;
    }
}
