package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DBAllowedUserRepository implements AllowedUserRepository{
    @PersistenceContext// EntityManagerFactory가 DI 할 수 있도록 어노테이션 설정
    private final EntityManager em;

    @Autowired
    public DBAllowedUserRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public AllowedUser findByAllowedUserID(String userID) {
        AllowedUser findAllowedUser=null;
        findAllowedUser=em.find(AllowedUser.class,userID);
        return findAllowedUser;

    }

    @Override
    public AllowedUser findByAllowedUserName(String userName) {
        AllowedUser findAllowedUser=null;
        findAllowedUser=em.find(AllowedUser.class,userName);
        return findAllowedUser;
    }


}
