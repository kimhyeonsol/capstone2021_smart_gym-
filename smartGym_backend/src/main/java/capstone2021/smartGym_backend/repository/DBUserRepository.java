package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DBUserRepository implements UserRepository{
    private final EntityManager em;

    public DBUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
