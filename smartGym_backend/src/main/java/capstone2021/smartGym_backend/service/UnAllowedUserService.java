package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.UnAllowedUser.*;
import capstone2021.smartGym_backend.domain.UnAllowedUser;

import java.util.List;

public interface UnAllowedUserService extends UserService{
    boolean unAllowedUserRegister(UnAllowedUserRegisterDTO unAllowedUserRegisterDTO) ; //회원가입
    boolean unAllowedUserIdDuplicateCheck(UnAllowedUserIdDuplDTO unAllowedUserIdDuplDTO);//id 중복 체크
    boolean unAllowedUserPhoneDuplicateCheck(UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO);//전화번호 중복 체크
    boolean unAllowedUserEmailDuplicateCheck(UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO);//이메일 중복 체크
    boolean unAllowedUserApprove(UnAllowedUserApproveDTO unAllowedUserApproveDTO);// 가입대기 사용자 승인
    List<UnAllowedUser> unAllowedUserReadAll(); //가입대기 사용자 전체 조회
    List<UnAllowedUser> unAllowedUserReadByID(UnAllowedUserReadByIDDTO unAllowedUserReadByIDDTO); //가입대기 사용자 ID 조회
    List<UnAllowedUser> unAllowedUserReadByName(UnAllowedUserReadByNameDTO unAllowedUserReadByNameDTO); //가입대기 사용자 이름 조회
}
