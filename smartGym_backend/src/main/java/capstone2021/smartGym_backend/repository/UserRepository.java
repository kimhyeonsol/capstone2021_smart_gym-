package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;

public interface UserRepository {
    User findByUserID(String userID);
    User findByUserPhone(String userPhone);
    User findByUserEmail(String userEmail);//이메일 중복 체크
}
