package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.User.UserIdDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserPhoneDuplDTO;


public interface UserService {
    boolean userIdDuplicateCheck(UserIdDuplDTO userIdDuplDTO);//id 중복 체크
    boolean userPhoneDuplicateCheck(UserPhoneDuplDTO userPhoneDuplDTO);//전화번호 중복 체크
    boolean userEmailDuplicateCheck(UserEmailDuplDTO unAllowedUserEmailDuplDTO);//이메일 중복 체크
}
