package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;
import capstone2021.smartGym_backend.service.AllowedUserService;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
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
        allowedUserLoginDTO1.setUserID("1");
        allowedUserLoginDTO1.setUserPW("1");

        System.out.println(allowedUserService.allowedUserLogin(allowedUserLoginDTO1));

        ///////////////////////////
    }
}
