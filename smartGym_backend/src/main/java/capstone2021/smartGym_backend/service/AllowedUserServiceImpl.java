package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindIDDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserFindPWDTO;
import capstone2021.smartGym_backend.DTO.AllowedUser.AllowedUserLoginDTO;
import capstone2021.smartGym_backend.DTO.User.UserDeleteDTO;
import capstone2021.smartGym_backend.DTO.User.UserUpdateDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.repository.AllowedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional

public class AllowedUserServiceImpl implements AllowedUserService{
    AllowedUserRepository allowedUserRepository;

    @Autowired
    public AllowedUserServiceImpl(AllowedUserRepository allowedUserRepository) {
        this.allowedUserRepository=allowedUserRepository;
    }

    @Override
    public int allowedUserLogin(AllowedUserLoginDTO allowedUserLoginDTO) {

        AllowedUser allowedUser = new AllowedUser();
        allowedUser.setUserID(allowedUserLoginDTO.getUserID());
        allowedUser.setUserPW(allowedUserLoginDTO.getUserPW());

        return allowedUserRepository.login(allowedUser);
    }

    @Override
    public String allowedUserFindID(AllowedUserFindIDDTO allowedUserFindIDDTO) {

        AllowedUser allowedUser = new AllowedUser();
        allowedUser.setUserName(allowedUserFindIDDTO.getUserName());
        allowedUser.setUserEmail(allowedUserFindIDDTO.getUserEmail());

        return allowedUserRepository.findID(allowedUser);
    }

    @Override
    public String allowedUserFindPW(AllowedUserFindPWDTO allowedUserFindPWDTO) {
        AllowedUser allowedUser = new AllowedUser();
        allowedUser.setUserID(allowedUserFindPWDTO.getUserID());
        allowedUser.setUserEmail(allowedUserFindPWDTO.getUserEmail());

        return allowedUserRepository.findPW(allowedUser);
    }


    @Override
    public boolean update(UserUpdateDTO userUpdateDTO) {
        return false;
    }

    @Override
    public boolean delete(UserDeleteDTO userDeleteDTO) {
        return false;
    }
}
