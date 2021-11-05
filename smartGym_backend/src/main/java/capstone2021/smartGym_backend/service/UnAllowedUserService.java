package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.UnAllowedUser.*;

public interface UnAllowedUserService extends UserService{
    boolean unAllowedUserRegister(UnAllowedUserRegisterDTO unAllowedUserRegisterDTO) ; //회원가입
    boolean unAllowedUserIdDuplicateCheck(UnAllowedUserIdDuplDTO unAllowedUserIdDuplDTO);//id 중복 체크
    boolean unAllowedUserPhoneDuplicateCheck(UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO);//전화번호 중복 체크
    boolean unAllowedUserEmailDuplicateCheck(UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO);//이메일 중복 체크
    boolean unAllowedUserApprove(UnAllowedUserApproveDTO unAllowedUserApproveDTO);// 가입대기 사용자 승인
}
