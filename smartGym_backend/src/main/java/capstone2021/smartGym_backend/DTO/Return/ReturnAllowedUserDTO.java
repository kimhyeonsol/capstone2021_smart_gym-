package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentSearchByCategoryDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;

public class ReturnAllowedUserDTO {
    Boolean success=true;
    AllowedUser data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AllowedUser getData() {
        return data;
    }

    public void setData(AllowedUser data) {
        this.data = data;
    }
}
