package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;

public interface AllowedUserService extends UserService{
    int allowedUserLogin(AllowedUserLoginDTO allowedUserLoginDTO) ; //로그인

}
