package capstone2021.smartGym_backend.service;

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
    public boolean managerLogin(ManagerLoginDTO managerLoginDTO) {
        Manager manager = new Manager();
        manager.setManagerPassword(managerLoginDTO.getManagerPassword());

        return managerRepository.managerLogin(manager);
    }
}
