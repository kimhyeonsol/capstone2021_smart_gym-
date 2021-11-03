package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.User.UserDeleteDTO;
import capstone2021.smartGym_backend.DTO.User.UserUpdateDTO;
import capstone2021.smartGym_backend.domain.User;
import capstone2021.smartGym_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
