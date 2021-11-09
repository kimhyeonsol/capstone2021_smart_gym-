package capstone2021.smartGym_backend.DTO.Return;

import java.util.List;

public class ReturnIntegerListDTO {
    Boolean success=true;
    List<Integer> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
