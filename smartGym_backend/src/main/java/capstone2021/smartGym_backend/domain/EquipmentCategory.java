package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EquipmentCategory implements Serializable {
    @Id //식별자
    @OneToOne
    @JoinColumn(name="equipment_category_id")
    @NotNull
    private Equipment equipmentCategoryID;

    @Column(name="equipment_category_chest", length = 11, columnDefinition = "int(11) default 0") //가슴
    @NotNull //널 허용 X
    private int equipmentCategoryChest = 0;

    @Column(name="equipment_category_back", length = 11, columnDefinition = "int(11) default 0") //등
    @NotNull //널 허용 X
    private int equipmentCategoryBack = 0;

    @Column(name="equipment_category_neck", length = 11, columnDefinition = "int(11) default 0") //목
    @NotNull //널 허용 X
    private int equipmentCategoryNeck = 0;

    @Column(name="equipment_category_stomach", length = 11, columnDefinition = "int(11) default 0") //복부
    @NotNull //널 허용 X
    private int equipmentCategoryStomach = 0;

    @Column(name="equipment_category_triceps", length = 11, columnDefinition = "int(11) default 0") //삼두
    @NotNull //널 허용 X
    private int equipmentCategoryTriceps = 0;

    @Column(name="equipment_category_trapezius", length = 11, columnDefinition = "int(11) default 0") //승모근
    @NotNull //널 허용 X
    private int equipmentCategoryTrapezius = 0;

    @Column(name="equipment_category_shoulder", length = 11, columnDefinition = "int(11) default 0") //어깨
    @NotNull //널 허용 X
    private int equipmentCategoryShoulder = 0;

    @Column(name="equipment_category_aerobic", length = 11, columnDefinition = "int(11) default 0") //유산소
    @NotNull //널 허용 X
    private int equipmentCategoryAerobic = 0;

    @Column(name="equipment_category_biceps", length = 11, columnDefinition = "int(11) default 0") //이두
    @NotNull //널 허용 X
    private int equipmentCategoryBiceps = 0;

    @Column(name="equipment_category_lower_body", length = 11, columnDefinition = "int(11) default 0") //하체
    @NotNull //널 허용 X
    private int equipmentCategoryLowerBody = 0;

    @Column(name="equipment_category_waist", length = 11, columnDefinition = "int(11) default 0") //허리
    @NotNull //널 허용 X
    private int equipmentCategoryWaist = 0;

    @Column(name="equipment_category_etc", length = 11, columnDefinition = "int(11) default 0") //기타
    @NotNull //널 허용 X
    private int equipmentCategoryEtc = 0;

    public Equipment getEquipmentCategoryID() {
        return equipmentCategoryID;
    }

    public void setEquipmentCategoryID(Equipment equipmentCategoryID) {
        this.equipmentCategoryID = equipmentCategoryID;
    }
    
    public int getEquipmentCategoryChest() {
        return equipmentCategoryChest;
    }

    public void setEquipmentCategoryChest(int equipmentCategoryChest) {
        this.equipmentCategoryChest = equipmentCategoryChest;
    }

    public int getEquipmentCategoryBack() {
        return equipmentCategoryBack;
    }

    public void setEquipmentCategoryBack(int equipmentCategoryBack) {
        this.equipmentCategoryBack = equipmentCategoryBack;
    }

    public int getEquipmentCategoryNeck() {
        return equipmentCategoryNeck;
    }

    public void setEquipmentCategoryNeck(int equipmentCategoryNeck) {
        this.equipmentCategoryNeck = equipmentCategoryNeck;
    }

    public int getEquipmentCategoryStomach() {
        return equipmentCategoryStomach;
    }

    public void setEquipmentCategoryStomach(int equipmentCategoryStomach) {
        this.equipmentCategoryStomach = equipmentCategoryStomach;
    }

    public int getEquipmentCategoryTriceps() {
        return equipmentCategoryTriceps;
    }

    public void setEquipmentCategoryTriceps(int equipmentCategoryTriceps) {
        this.equipmentCategoryTriceps = equipmentCategoryTriceps;
    }

    public int getEquipmentCategoryTrapezius() {
        return equipmentCategoryTrapezius;
    }

    public void setEquipmentCategoryTrapezius(int equipmentCategoryTrapezius) {
        this.equipmentCategoryTrapezius = equipmentCategoryTrapezius;
    }

    public int getEquipmentCategoryShoulder() {
        return equipmentCategoryShoulder;
    }

    public void setEquipmentCategoryShoulder(int equipmentCategoryShoulder) {
        this.equipmentCategoryShoulder = equipmentCategoryShoulder;
    }

    public int getEquipmentCategoryAerobic() {
        return equipmentCategoryAerobic;
    }

    public void setEquipmentCategoryAerobic(int equipmentCategoryAerobic) {
        this.equipmentCategoryAerobic = equipmentCategoryAerobic;
    }

    public int getEquipmentCategoryBiceps() {
        return equipmentCategoryBiceps;
    }

    public void setEquipmentCategoryBiceps(int equipmentCategoryBiceps) {
        this.equipmentCategoryBiceps = equipmentCategoryBiceps;
    }

    public int getEquipmentCategoryLowerBody() {
        return equipmentCategoryLowerBody;
    }

    public void setEquipmentCategoryLowerBody(int equipmentCategoryLowerBody) {
        this.equipmentCategoryLowerBody = equipmentCategoryLowerBody;
    }

    public int getEquipmentCategoryWaist() {
        return equipmentCategoryWaist;
    }

    public void setEquipmentCategoryWaist(int equipmentCategoryWaist) {
        this.equipmentCategoryWaist = equipmentCategoryWaist;
    }

    public int getEquipmentCategoryEtc() {
        return equipmentCategoryEtc;
    }

    public void setEquipmentCategoryEtc(int equipmentCategoryEtc) {
        this.equipmentCategoryEtc = equipmentCategoryEtc;
    }
}
