package capstone2021.smartGym_backend.DTO.Equipment;

import capstone2021.smartGym_backend.domain.Equipment;

import java.util.List;

public class EquipmentSearchByCategoryDTO {
    private List<Equipment> chest=null;
    private List<Equipment> back=null;
    private List<Equipment> neck=null;
    private List<Equipment> stomach=null;
    private List<Equipment> triceps=null;//삼두
    private List<Equipment> trapezius=null;//승모근
    private List<Equipment> shoulder=null;
    private List<Equipment> aerobic=null;//유산소
    private List<Equipment> biceps=null;//이두
    private List<Equipment> lower_body =null;
    private List<Equipment> waist=null;//허리
    private List<Equipment> etc=null;//기타

    public List<Equipment> getChest() {
        return chest;
    }

    public void setChest(List<Equipment> chest) {
        this.chest = chest;
    }

    public List<Equipment> getBack() {
        return back;
    }

    public void setBack(List<Equipment> back) {
        this.back = back;
    }

    public List<Equipment> getNeck() {
        return neck;
    }

    public void setNeck(List<Equipment> neck) {
        this.neck = neck;
    }

    public List<Equipment> getStomach() {
        return stomach;
    }

    public void setStomach(List<Equipment> stomach) {
        this.stomach = stomach;
    }

    public List<Equipment> getTriceps() {
        return triceps;
    }

    public void setTriceps(List<Equipment> triceps) {
        this.triceps = triceps;
    }

    public List<Equipment> getTrapezius() {
        return trapezius;
    }

    public void setTrapezius(List<Equipment> trapezius) {
        this.trapezius = trapezius;
    }

    public List<Equipment> getShoulder() {
        return shoulder;
    }

    public void setShoulder(List<Equipment> shoulder) {
        this.shoulder = shoulder;
    }

    public List<Equipment> getAerobic() {
        return aerobic;
    }

    public void setAerobic(List<Equipment> aerobic) {
        this.aerobic = aerobic;
    }

    public List<Equipment> getBiceps() {
        return biceps;
    }

    public void setBiceps(List<Equipment> biceps) {
        this.biceps = biceps;
    }

    public List<Equipment> getLower_body() {
        return lower_body;
    }

    public void setLower_body(List<Equipment> lower_body) {
        this.lower_body = lower_body;
    }

    public List<Equipment> getWaist() {
        return waist;
    }

    public void setWaist(List<Equipment> waist) {
        this.waist = waist;
    }

    public List<Equipment> getEtc() {
        return etc;
    }

    public void setEtc(List<Equipment> etc) {
        this.etc = etc;
    }
}
