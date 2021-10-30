package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserIdDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserPhoneDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UnAllowedUserApplicationTests extends SmartGymBackendApplicationTests{
    @Autowired
    UnAllowedUserService unAllowedUserService;


    @Test
    @Commit
    void register테스트()  {

        UnAllowedUserRegisterDTO unAllowedUserRegisterDTO1= new UnAllowedUserRegisterDTO();
        unAllowedUserRegisterDTO1.setUserID("1");
        unAllowedUserRegisterDTO1.setUserPW("1");
        unAllowedUserRegisterDTO1.setUserName("1");
        unAllowedUserRegisterDTO1.setUserSex("여성");
        unAllowedUserRegisterDTO1.setUserPhone("1");
        unAllowedUserRegisterDTO1.setUserEmail("1");

        System.out.println(unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO1));

        ///////////////////////////

        UnAllowedUserRegisterDTO unAllowedUserRegisterDTO2= new UnAllowedUserRegisterDTO();
        unAllowedUserRegisterDTO2.setUserID("2");
        unAllowedUserRegisterDTO2.setUserPW("2");
        unAllowedUserRegisterDTO2.setUserName("2");
        unAllowedUserRegisterDTO2.setUserSex("여성");
        unAllowedUserRegisterDTO2.setUserPhone("2");
        unAllowedUserRegisterDTO2.setUserEmail("2");

        System.out.println(unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO2));


        ///////////////////////////

        UnAllowedUserRegisterDTO unAllowedUserRegisterDTO3= new UnAllowedUserRegisterDTO();
        unAllowedUserRegisterDTO3.setUserID("3");
        unAllowedUserRegisterDTO3.setUserPW("3");
        unAllowedUserRegisterDTO3.setUserName("3");
        unAllowedUserRegisterDTO3.setUserSex("여성");
        unAllowedUserRegisterDTO3.setUserPhone("3");
        unAllowedUserRegisterDTO3.setUserEmail("3");

        System.out.println(unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO3));


        ////////////////////////////

        UnAllowedUserRegisterDTO unAllowedUserRegisterDTO4= new UnAllowedUserRegisterDTO();
        unAllowedUserRegisterDTO4.setUserID("4");
        unAllowedUserRegisterDTO4.setUserPW("4");
        unAllowedUserRegisterDTO4.setUserName("4");
        unAllowedUserRegisterDTO4.setUserSex("여성");
        unAllowedUserRegisterDTO4.setUserPhone("4");
        unAllowedUserRegisterDTO4.setUserEmail("4");

        System.out.println(unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO4));

        ////////////////////////////

        UnAllowedUserRegisterDTO unAllowedUserRegisterDTO5= new UnAllowedUserRegisterDTO();
        unAllowedUserRegisterDTO5.setUserID("5");
        unAllowedUserRegisterDTO5.setUserPW("5");
        unAllowedUserRegisterDTO5.setUserName("5");
        unAllowedUserRegisterDTO5.setUserSex("여성");
        unAllowedUserRegisterDTO5.setUserPhone("5");
        unAllowedUserRegisterDTO5.setUserEmail("5");

        System.out.println(unAllowedUserService.unAllowedUserRegister(unAllowedUserRegisterDTO5));
    }

    @Test
    @Commit
    void 아이디_중복_테스트() {
        UnAllowedUserIdDuplDTO unAllowedUserIdDuplDTO= new UnAllowedUserIdDuplDTO();
        unAllowedUserIdDuplDTO.setUserID("7");

        System.out.println(unAllowedUserService.unAllowedUserIdDuplicateCheck(unAllowedUserIdDuplDTO));
    }

    @Test
    @Commit
    void 전화번호_중복_테스트() {
        UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO= new UnAllowedUserPhoneDuplDTO();
        unAllowedUserPhoneDuplDTO.setUserPhone("7");

        System.out.println(unAllowedUserService.unAllowedUserPhoneDuplicateCheck(unAllowedUserPhoneDuplDTO));
    }

    @Test
    @Commit
    void 이메일_중복_테스트() {
        UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO= new UnAllowedUserEmailDuplDTO();
        unAllowedUserEmailDuplDTO.setUserEmail("7");

        System.out.println(unAllowedUserService.unAllowedUserEmailDuplicateCheck(unAllowedUserEmailDuplDTO));
    }
}
