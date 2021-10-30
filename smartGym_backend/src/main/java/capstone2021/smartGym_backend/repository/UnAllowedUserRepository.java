package capstone2021.smartGym_backend.repository;


import capstone2021.smartGym_backend.domain.UnAllowedUser;

public interface UnAllowedUserRepository {
    boolean register(UnAllowedUser unAllowedUser);//회원가입
    boolean idDuplicateCheck(String userID);//아이디 중복체크
    boolean phoneDuplicateCheck(String userPhone);//핸드폰 번호 중복 체크
    boolean emailDuplicateCheck(String userEmail);//이메일 중복 체크
}
