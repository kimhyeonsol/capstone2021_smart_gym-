package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.AllowedUser.*;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserReadByIDDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserReadByNameDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;

import java.util.List;

public interface AllowedUserService extends UserService{
    int allowedUserLogin(AllowedUserLoginDTO allowedUserLoginDTO); //로그인
    String allowedUserFindID(AllowedUserFindIDDTO allowedUserFindIDDTO); //아이디찾기
    String allowedUserFindPW(AllowedUserFindPWDTO allowedUserFindPWDTO); //비밀번호찾기
    List<AllowedUser> allowedUserReadAll(); //가입대기 사용자 전체 조회
    List<AllowedUser> allowedUserReadByID(AllowedUserReadByIDDTO allowedUserReadByIDDTO); //가입대기 사용자 ID 조회
    List<AllowedUser> allowedUserReadByName(AllowedUserReadByNameDTO allowedUserReadByNameDTO); //가입대기 사용자 이름 조회
}
