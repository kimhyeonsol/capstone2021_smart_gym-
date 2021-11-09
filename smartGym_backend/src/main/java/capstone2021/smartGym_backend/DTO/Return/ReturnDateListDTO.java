package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.domain.AllowedUser;

import java.util.Date;
import java.util.List;

public class ReturnDateListDTO {
    Boolean success=true;
    List<String> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
