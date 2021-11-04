package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Manager.ManagerLoginDTO;

public interface ManagerService {
    int managerLogin(ManagerLoginDTO managerLoginDTO);
}
