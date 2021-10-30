package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.User;

public interface UserRepository {

    boolean update(User user);
    boolean delete(User user);
}
