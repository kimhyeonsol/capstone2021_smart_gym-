package capstone2021.smartGym_backend.DTO;

import capstone2021.smartGym_backend.domain.Equipment;

public class EquipmentDetailedReadResponseDTO {
    private Long equipmentID;
    private String equipmentName;
    private String equipmentNameNth;
    private String equipmentCategoryList;
    private String equipmentImage;
    private int equipmentAvailable;

    private Equipment equipmentCategoryID;
    private int equipmentCategoryChest;
    private int equipmentCategoryBack;
    private int equipmentCategoryNeck;
    private int equipmentCategoryStomach;
    private int equipmentCategoryTriceps;
    private int equipmentCategoryTrapezius;
    private int equipmentCategoryShoulder;
    private int equipmentCategoryAerobic;
    private int equipmentCategoryBiceps;
    private int equipmentCategoryLowerBody;
    private int equipmentCategoryWaist;
    private int equipmentCategoryEtc;

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
