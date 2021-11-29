package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
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
    public User findByUserID(String userID) {
        User findUser=null;
        findUser=em.find(User.class,userID);
        return findUser;
    }

    @Override
    public User findByUserPhone(String userPhone) {
        User findUser=null;
        findUser=em.find(User.class,userPhone);
        return findUser;
    }

    @Override
    public User findByUserEmail(String userEmail) {
        User findUser=null;
        findUser=em.find(User.class,userEmail);
        return findUser;
    }

}
