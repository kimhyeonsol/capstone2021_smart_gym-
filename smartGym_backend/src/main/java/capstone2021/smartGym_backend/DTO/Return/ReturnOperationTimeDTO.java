package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.DTO.Reservation.ReservationReadOperatingTimeDTO;

public class ReturnOperationTimeDTO {
    Boolean success=true;
    ReservationReadOperatingTimeDTO data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ReservationReadOperatingTimeDTO getData() {
        return data;
    }

    public void setData(ReservationReadOperatingTimeDTO data) {
        this.data = data;
    }
}
