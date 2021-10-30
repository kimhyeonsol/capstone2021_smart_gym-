package capstone2021.smartGym_backend.repository;

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
public class DBUnAllowedUserRepository implements UnAllowedUserRepository{
    @PersistenceContext// EntityManagerFactory가 DI 할 수 있도록 어노테이션 설정
    private final EntityManager em;

    @Autowired
    public DBUnAllowedUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean register(UnAllowedUser unAllowedUser) {
        em.persist(unAllowedUser);
        return true;
    }

    @Override
    public boolean idDuplicateCheck(String userID) {
        List<User> checkDupl;

        if(userID != null){
            checkDupl = em.createQuery("SELECT u FROM User u WHERE userID = :userID",User.class)
                    .setParameter("userID", userID).getResultList();
            if(checkDupl.isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean phoneDuplicateCheck(String userPhone) {
        List<User> checkDupl;

        if(userPhone != null){
            checkDupl = em.createQuery("SELECT u FROM User u WHERE userPhone = :userPhone",User.class)
                    .setParameter("userPhone", userPhone).getResultList();
            if(checkDupl.isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean emailDuplicateCheck(String userEmail) {
        List<User> checkDupl;

        if(userEmail != null){
            checkDupl = em.createQuery("SELECT u FROM User u WHERE userEmail = :userEmail",User.class)
                    .setParameter("userEmail", userEmail).getResultList();
            if(checkDupl.isEmpty()){
                return true;
            }
        }
        return false;
    }
}
