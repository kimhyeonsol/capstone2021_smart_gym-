package capstone2021.smartGym_backend.DTO.Return;

import java.util.Date;
import java.util.List;

public class ReturnDateListDTO {
    Boolean success=true;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Date> getData() {
        return data;
    }

    public void setData(List<Date> data) {
        this.data = data;
    }

    List<Date> data;
}
