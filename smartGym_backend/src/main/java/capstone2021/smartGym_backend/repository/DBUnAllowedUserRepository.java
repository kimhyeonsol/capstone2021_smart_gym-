package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DBUnAllowedUserRepository implements UnAllowedUserRepository {
    @PersistenceContext// EntityManagerFactory가 DI 할 수 있도록 어노테이션 설정
    private final EntityManager em;

    @Autowired
    public DBUnAllowedUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Boolean save(UnAllowedUser unAllowedUser) {

        if(findByUserID(unAllowedUser.getUserID())==null) {
            em.persist(unAllowedUser);
            return true;
        }
        return false;
    }


    @Override
    public UnAllowedUser findByUnAllowedUserID(String userID) {
        UnAllowedUser findUnAllowedUser=null;

        findUnAllowedUser=em.find(UnAllowedUser.class,userID);
        return findUnAllowedUser;
    }



    @Override
    public UnAllowedUser deleteByID(String userID) {
        UnAllowedUser findUnAllowedUser=null;
        UnAllowedUser deletedUser=null;

        findUnAllowedUser=findByUnAllowedUserID(userID);
        if(findUnAllowedUser!=null) {
            deletedUser=findUnAllowedUser;
            em.remove(findUnAllowedUser);
            return deletedUser;
        }
        return null;
    }

    @Override
    public List<UnAllowedUser> unAllowedUserReadAll() {
        return em.createQuery("SELECT u FROM UnAllowedUser u", UnAllowedUser.class)
                .getResultList();
    }

    @Override
    public List<UnAllowedUser> unAllowedUserReadByID(UnAllowedUser unAllowedUser) {
        return em.createQuery("SELECT u FROM UnAllowedUser u WHERE u.userID = :unAllowedUser")
                .setParameter("unAllowedUser", unAllowedUser.getUserID()).getResultList();
    }

    @Override
    public List<UnAllowedUser> unAllowedUserReadByName(UnAllowedUser unAllowedUser) {
        return em.createQuery("SELECT u FROM UnAllowedUser u WHERE u.userName = :unAllowedUser")
                .setParameter("unAllowedUser", unAllowedUser.getUserName()).getResultList();
    }

    public User findByUserID(String userID) {
        User findUser=null;
        findUser=em.find(User.class,userID);
        return findUser;
    }


}
