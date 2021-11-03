package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.domain.AllowedUser;

public class ReturnIntDTO {
    Boolean success=true;
    int data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
