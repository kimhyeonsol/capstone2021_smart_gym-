package capstone2021.smartGym_backend.repository;


import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;

import java.util.List;

public interface UnAllowedUserRepository {
    Boolean save(UnAllowedUser unAllowedUser);
    UnAllowedUser findByUnAllowedUserID(String userID);
    UnAllowedUser findByUnAllowedUserPhone(String userPhone);
    UnAllowedUser findByUnAllowedUserEmail(String userEmail);//이메일 중복 체크
}
