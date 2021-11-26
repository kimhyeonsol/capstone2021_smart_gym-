package capstone2021.smartGym_backend.repository;


import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;

import java.util.List;

public interface UnAllowedUserRepository {
    Boolean save(UnAllowedUser unAllowedUser);
    UnAllowedUser findByUnAllowedUserID(String userID);
    UnAllowedUser deleteByID(String userID);
    List<UnAllowedUser> unAllowedUserReadAll();
    List<UnAllowedUser> unAllowedUserReadByID(UnAllowedUser unAllowedUser);
    List<UnAllowedUser> unAllowedUserReadByName(UnAllowedUser unAllowedUser);
}
