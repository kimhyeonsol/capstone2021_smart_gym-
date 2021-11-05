package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.domain.AllowedUser;

import java.util.List;

public class ReturnAllowedUserListDTO {
    Boolean success=true;
    List<AllowedUser> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<AllowedUser> getData() {
        return data;
    }

    public void setData(List<AllowedUser> data) {
        this.data = data;
    }
}
