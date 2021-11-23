package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.domain.AllowedUser;

public class ReturnFloatDTO {
    Boolean success=true;
    float data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }
}
