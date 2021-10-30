package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindIDDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindPWDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;

public interface AllowedUserService extends UserService{
    int allowedUserLogin(AllowedUserLoginDTO allowedUserLoginDTO); //로그인
    String allowedUserFindID(AllowedUserFindIDDTO allowedUserFindIDDTO); //아이디찾기
    String allowedUserFindPW(AllowedUserFindPWDTO allowedUserFindPWDTO); //아이디찾기
}
