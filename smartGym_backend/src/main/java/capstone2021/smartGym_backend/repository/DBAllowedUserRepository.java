package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    public int login(AllowedUser allowedUser) {

        //가입 승인 사용자 테이블에서 탐색
        UnAllowedUser findAllowedUser =em.find(UnAllowedUser.class,allowedUser.getUserID());
        if(findAllowedUser!=null) {
            if(findAllowedUser.getUserPW()==allowedUser.getUserPW()) {
                return 0;
            }
            else {
                return 1;
            }
        }

        //가입 대기 사용자 테이블에서 탐색
        UnAllowedUser findUnAllowedUser =em.find(UnAllowedUser.class, allowedUser.getUserID());
        if(findUnAllowedUser!=null) {
            return 2;
        }
        return 3;
    }

}
