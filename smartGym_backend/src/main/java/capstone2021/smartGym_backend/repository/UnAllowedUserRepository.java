package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.UnAllowedUser;

public interface UnAllowedUserRepository {
    boolean register(UnAllowedUser unAllowedUser);
    boolean idDuplicateCheck(String userID);
    boolean phoneDuplicateCheck(String userPhone);
    boolean emailDuplicateCheck(String userEmail);
}
