package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Manager;

public interface ManagerRepository {
    Manager read();
    int managerLogin(Manager manager);
    boolean managerSaveLoginStatus(Manager manager);
    boolean managerIsLogin();
}
