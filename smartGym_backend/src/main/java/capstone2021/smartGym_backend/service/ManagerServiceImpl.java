package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Manager.ManagerCheckLoginDTO;
import capstone2021.smartGym_backend.DTO.Manager.ManagerLoginDTO;
import capstone2021.smartGym_backend.domain.Manager;
import capstone2021.smartGym_backend.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public int managerLogin(ManagerLoginDTO managerLoginDTO) {
        if(managerLoginDTO.getManagerPassword() == null || managerLoginDTO.getManagerPassword().isBlank()){
            return 1;
        }
        Manager manager = new Manager();
        manager.setManagerPassword(managerLoginDTO.getManagerPassword());

        return managerRepository.managerLogin(manager);
    }

    @Override
    public boolean managerCheckLogin(ManagerCheckLoginDTO managerCheckLoginDTO) {
        Manager findManager = managerRepository.read();
        findManager.setManagerCheckLogin(managerCheckLoginDTO.getManagerCheckLogin());

        return managerRepository.managerCheckLogin(findManager);
    }

    @Override
    public boolean managerIsLogin() {
        return managerRepository.managerIsLogin();
    }
}
