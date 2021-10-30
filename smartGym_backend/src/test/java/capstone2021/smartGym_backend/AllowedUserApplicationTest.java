package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindIDDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindPWDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.service.AllowedUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AllowedUserApplicationTest extends SmartGymBackendApplicationTests{

    @Autowired
    AllowedUserService allowedUserService;

    @Test
    @Commit
    void 로그인테스트() {

        AllowedUserLoginDTO allowedUserLoginDTO1 = new AllowedUserLoginDTO();
        allowedUserLoginDTO1.setUserID("6");
        allowedUserLoginDTO1.setUserPW("6");

        System.out.println(allowedUserService.allowedUserLogin(allowedUserLoginDTO1));

        ///////////////////////////
    }

    @Test
    @Commit
    void 아이디찾기테스트() {

        AllowedUserFindIDDTO allowedUserFindIDDTO = new AllowedUserFindIDDTO();
        allowedUserFindIDDTO.setUserName("1");
        allowedUserFindIDDTO.setUserEmail("1");

        System.out.println(allowedUserService.allowedUserFindID(allowedUserFindIDDTO));

        ///////////////////////////
    }

    @Test
    @Commit
    void 비밀번호찾기테스트() {

        AllowedUserFindPWDTO allowedUserFindPWDTO = new AllowedUserFindPWDTO();
        allowedUserFindPWDTO.setUserID("1");
        allowedUserFindPWDTO.setUserEmail("1");

        System.out.println(allowedUserService.allowedUserFindPW(allowedUserFindPWDTO));

        ///////////////////////////
    }
}
