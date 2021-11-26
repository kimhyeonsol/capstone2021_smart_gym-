package capstone2021.smartGym_backend.domain;

import javax.persistence.*;

@Entity
public class ESL{
    @Id //식별자
    @Column(name="esl_id", length = 200)
    private String eslID;

    @Column(name="equipment_id")
    private Long equipmentID=null;

    @Column(name="reservation_id")
    private Long reservationID=null;

    public String getEslID() {
        return eslID;
    }

    public void setEslID(String eslID) {
        this.eslID = eslID;
    }

    public Long getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Long equipmentID) {
        this.equipmentID = equipmentID;
    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }
}