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



    @Override
    public boolean update(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setUserID(userUpdateDTO.getUserID());
        user.setUserPW(userUpdateDTO.getUserPW());
        user.setUserName(userUpdateDTO.getUserName());
        user.setUserSex(userUpdateDTO.getUserSex());
        user.setUserPhone(userUpdateDTO.getUserPhone());
        user.setUserEmail(userUpdateDTO.getUserEmail());

        return userRepository.update(user);
    }

    @Override
    public boolean delete(UserDeleteDTO userDeleteDTO) {
        User user = new User();
        user.setUserID(userDeleteDTO.getUserID());
        user.setUserPW(userDeleteDTO.getUserPW());

        // userID랑 pw 같은지 학인하는 메소드 어떻게 할지.

        return userRepository.delete(user);
    }

}
