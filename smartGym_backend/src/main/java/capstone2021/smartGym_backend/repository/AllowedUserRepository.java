package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.AllowedUser;

public interface AllowedUserRepository {
    int login(AllowedUser allowedUser);//로그인
}
