package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

//@Entity
public class ESL {
    @Id //식별자
    @OneToOne
    @JoinColumn(name="equipment_id") //크기
    @NotNull //널 허용 X
    private Equipment equipmentID;

    @OneToOne
    @JoinColumn(name="reservation_id") //크기
    @NotNull //널 허용 X
    private Reservation reservationID;

    public Equipment getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Equipment equipmentID) {
        this.equipmentID = equipmentID;
    }

    public Reservation getReservationID() {
        return reservationID;
    }

    public void setReservationID(Reservation reservationID) {
        this.reservationID = reservationID;
    }
}