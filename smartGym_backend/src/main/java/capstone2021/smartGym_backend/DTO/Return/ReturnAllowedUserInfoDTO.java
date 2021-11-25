package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserInfoDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;

public class ReturnAllowedUserInfoDTO {
    Boolean success=true;
    AllowedUserInfoDTO data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AllowedUserInfoDTO getData() {
        return data;
    }

    public void setData(AllowedUserInfoDTO data) {
        this.data = data;
    }
}
