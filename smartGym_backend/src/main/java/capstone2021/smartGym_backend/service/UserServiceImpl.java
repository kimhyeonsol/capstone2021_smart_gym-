package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.User.UserIdDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserPhoneDuplDTO;
import capstone2021.smartGym_backend.domain.User;
import capstone2021.smartGym_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean userIdDuplicateCheck(UserIdDuplDTO userIdDuplDTO) {
        User findUserByID = null;
        findUserByID = userRepository.findByUserID(userIdDuplDTO.getUserID());
        if (findUserByID == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userPhoneDuplicateCheck(UserPhoneDuplDTO userPhoneDuplDTO) {
        User findUserByPhone = null;
        findUserByPhone = userRepository.findByUserPhone(userPhoneDuplDTO.getUserPhone());
        if (findUserByPhone == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userEmailDuplicateCheck(UserEmailDuplDTO unAllowedUserEmailDuplDTO) {
        User findUserByEmail = null;
        findUserByEmail = userRepository.findByUserEmail(unAllowedUserEmailDuplDTO.getUserEmail());
        if (findUserByEmail == null) {
            return true;
        }
        return false;
    }


}
