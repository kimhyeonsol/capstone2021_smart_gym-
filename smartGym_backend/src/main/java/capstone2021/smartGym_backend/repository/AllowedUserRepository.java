package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;

import java.util.List;

public interface AllowedUserRepository {
    //int login(AllowedUser allowedUser);//로그인
//    String findID(AllowedUser allowedUser);//아이디찾기
//    String findPW(AllowedUser allowedUser);//비밀번호찾기
    AllowedUser findByAllowedUserID(String userID);
    AllowedUser findByAllowedUserName(String userName);
    Boolean save(AllowedUser allowedUser);

}
