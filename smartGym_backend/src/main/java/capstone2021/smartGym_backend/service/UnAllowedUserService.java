package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserIdDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserPhoneDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;

public interface UnAllowedUserService extends UserService{
    boolean unAllowedUserRegister(UnAllowedUserRegisterDTO unAllowedUserRegisterDTO) ; //회원가입
    boolean unAllowedUserIdDuplicateCheck(UnAllowedUserIdDuplDTO unAllowedUserIdDuplDTO);//id 중복 체크
    boolean unAllowedUserPhoneDuplicateCheck(UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO);//전화번호 중복 체크
    boolean unAllowedUserEmailDuplicateCheck(UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO);//이메일 중복 체크

}
