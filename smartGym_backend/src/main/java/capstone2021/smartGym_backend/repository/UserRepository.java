package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.User;

public interface UserRepository {
    User findByUserID(String userID);
}
