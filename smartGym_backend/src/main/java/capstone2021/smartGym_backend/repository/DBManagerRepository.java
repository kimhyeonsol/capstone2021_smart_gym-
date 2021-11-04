package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Manager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DBManagerRepository implements ManagerRepository{
    private final EntityManager em;

    public DBManagerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int managerLogin(Manager manager) {
        Manager findManager = em.find(Manager.class, manager.getManagerPassword());

        if(findManager == null){
            return 2;
        }

        return 0;
    }
}
