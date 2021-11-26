package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Manager.ManagerSaveLoginStatusDTO;
import capstone2021.smartGym_backend.DTO.Manager.ManagerLoginDTO;

public interface ManagerService {
    int managerLogin(ManagerLoginDTO managerLoginDTO);
    boolean managerSaveLoginStatus(ManagerSaveLoginStatusDTO managerSaveLoginStatusDTO);
    boolean managerIsLogin();
}
