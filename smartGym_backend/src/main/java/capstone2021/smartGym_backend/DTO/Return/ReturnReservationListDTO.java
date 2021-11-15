package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.DTO.Reservation.SelectedDayReservationDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;

import java.util.List;

public class ReturnReservationListDTO {
    Boolean success=true;
    List<SelectedDayReservationDTO> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<SelectedDayReservationDTO> getData() {
        return data;
    }

    public void setData(List<SelectedDayReservationDTO> data) {
        this.data = data;
    }
}
