package capstone2021.smartGym_backend.DTO.Return;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentSearchByCategoryDTO;

import java.util.List;

public class ReturnEquipmentSearchByCategoryDTO {
    Boolean success=true;
    EquipmentSearchByCategoryDTO data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public EquipmentSearchByCategoryDTO getData() {
        return data;
    }

    public void setData(EquipmentSearchByCategoryDTO data) {
        this.data = data;
    }
}

