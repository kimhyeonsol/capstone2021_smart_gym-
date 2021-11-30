package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserUpdateDTO;
import capstone2021.smartGym_backend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
        List<AllowedUser> findAllowedUser=null;
        findAllowedUser=em.createQuery("SELECT u FROM AllowedUser u WHERE u.userName = :userName")
                .setParameter("userName", userName).getResultList();
        if(findAllowedUser.isEmpty())
            return null;

        return findAllowedUser.get(0);
    }

    @Override
    public Boolean save(AllowedUser allowedUser) {
        if(findByAllowedUserID(allowedUser.getUserID())==null) {
            em.persist(allowedUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean allowedUserReservationAuthorityUpdate(AllowedUser allowedUser) {
        try{
            em.merge(allowedUser);
            return true;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("update 오류");
            return false;
        }
    }

    @Override
    public List<AllowedUser> allowedUserReadAll() {
        return em.createQuery("SELECT u FROM AllowedUser u", AllowedUser.class)
                .getResultList();
    }

    @Override
    public List<AllowedUser> allowedUserReadByID(AllowedUser AllowedUser) {
        return em.createQuery("SELECT u FROM AllowedUser u WHERE u.userID like :allowedUserID")
                .setParameter("allowedUserID", "%"+AllowedUser.getUserID()+"%").getResultList();
    }

    @Override
    public List<AllowedUser> allowedUserReadByName(AllowedUser AllowedUser) {
        return em.createQuery("SELECT u FROM AllowedUser u WHERE u.userName like :allowedUserName")
                .setParameter("allowedUserName","%"+ AllowedUser.getUserName()+"%").getResultList();
    }

    @Override
    public AllowedUser delete(AllowedUser allowedUser) {
        List<Reservation> reservations;
        AllowedUser delete=allowedUser;
        reservations = em.createQuery("SELECT r FROM Reservation r WHERE r.userID IN (SELECT au.userID FROM AllowedUser au WHERE r.userID = :userID)", Reservation.class)
                .setParameter("userID", allowedUser).getResultList();

        try {
            if (em.contains(allowedUser)) {
                for(Reservation reservation : reservations){ //예약 다 삭제
                    em.remove(reservation);
                }
                em.remove(allowedUser);
            }
            else {
                for(Reservation reservation : reservations){
                    em.remove(em.merge(reservation));
                }
                em.remove(em.merge(allowedUser));
            }
            return delete;
        }
        catch (PersistenceException | IllegalStateException e){
            return null;
        }
    }

    @Override
    public AllowedUser update(AllowedUserUpdateDTO allowedUserUpdateDTO) {
        AllowedUser findUser=findByAllowedUserID(allowedUserUpdateDTO.getUserID());

        if(findUser != null) {
            try {
                findUser.setUserName(allowedUserUpdateDTO.getUserName());
                findUser.setUserPW(allowedUserUpdateDTO.getUserPW());
                findUser.setUserEmail(allowedUserUpdateDTO.getUserEmail());
                findUser.setUserPhone(allowedUserUpdateDTO.getUserPhone());
                findUser.setUserEmail(allowedUserUpdateDTO.getUserEmail());
                findUser.setUserSex(allowedUserUpdateDTO.getUserSex());

                return findUser;
            } catch (PersistenceException | IllegalStateException e) {
                System.out.println("update 오류");
                return null;
            }
        }
        return null;
    }

}
