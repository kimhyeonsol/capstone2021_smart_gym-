package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ESL{
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    @Column(name="esl_id") //크기
    private Long eslID;

    @Column(name="equipment_id")
    private Long equipmentID=null;

    @Column(name="reservation_id")
    private Long reservationID=null;

    public Long getEslID() {
        return eslID;
    }

    public void setEslID(Long eslID) {
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