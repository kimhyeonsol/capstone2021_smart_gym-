package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserUpdateDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;

import java.util.List;

public interface AllowedUserRepository {
    AllowedUser findByAllowedUserID(String userID);
    AllowedUser findByAllowedUserName(String userName);
    Boolean save(AllowedUser allowedUser);
    boolean allowedUserReservationAuthorityUpdate(AllowedUser allowedUser);
    List<AllowedUser> allowedUserReadAll();
    List<AllowedUser> allowedUserReadByID(AllowedUser AllowedUser);
    List<AllowedUser> allowedUserReadByName(AllowedUser AllowedUser);
    AllowedUser delete(AllowedUser allowedUser);
    AllowedUser update(AllowedUserUpdateDTO allowedUserUpdateDTO);
}
