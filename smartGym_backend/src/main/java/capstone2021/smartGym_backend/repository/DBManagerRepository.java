package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Manager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@Repository
public class DBManagerRepository implements ManagerRepository{
    private final EntityManager em;

    public DBManagerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Manager read() {
        return em.find(Manager.class, "0000");
    }

    @Override
    public int managerLogin(Manager manager) {
        Manager findManager = em.find(Manager.class, manager.getManagerPassword());

        if(findManager == null){
            return 2;
        }

        return 0;
    }

    @Override
    public boolean managerSaveLoginStatus(Manager manager) {
        try{
            em.merge(manager);
            return true;

        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return false;
        }
    }

    @Override
    public boolean managerIsLogin() {
        Manager findManager = read();

        if(findManager.getManagerLoginStatus().equals("true")){
            return true;
        }

        return false;
    }
}
