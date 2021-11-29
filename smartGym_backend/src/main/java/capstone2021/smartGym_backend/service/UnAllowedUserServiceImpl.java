package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.UnAllowedUser.*;
import capstone2021.smartGym_backend.DTO.User.UserDeleteDTO;
import capstone2021.smartGym_backend.DTO.User.UserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserPhoneDuplDTO;
import capstone2021.smartGym_backend.DTO.User.UserUpdateDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;
import capstone2021.smartGym_backend.repository.AllowedUserRepository;
import capstone2021.smartGym_backend.repository.UnAllowedUserRepository;
import capstone2021.smartGym_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UnAllowedUserServiceImpl extends UserServiceImpl implements UnAllowedUserService{

    UnAllowedUserRepository unAllowedUserRepository;
    AllowedUserRepository allowedUserRepository;
    UserRepository userRepository;


    @Autowired
    public UnAllowedUserServiceImpl(UnAllowedUserRepository unAllowedUserRepository,AllowedUserRepository allowedUserRepository,UserRepository userRepository) {
        super(userRepository);
        this.unAllowedUserRepository = unAllowedUserRepository;
        this.allowedUserRepository=allowedUserRepository;
        this.userRepository=userRepository;
    }

    @Override
    public boolean unAllowedUserRegister(UnAllowedUserRegisterDTO unAllowedUserRegisterDTO) {
        UnAllowedUser unAllowedUser = new UnAllowedUser();
        LocalDateTime now = LocalDateTime.now();

        unAllowedUser.setUserID(unAllowedUserRegisterDTO.getUserID());
        unAllowedUser.setUserPW(unAllowedUserRegisterDTO.getUserPW());
        unAllowedUser.setUserName(unAllowedUserRegisterDTO.getUserName());
        unAllowedUser.setUserSex(unAllowedUserRegisterDTO.getUserSex());
        unAllowedUser.setUserPhone(unAllowedUserRegisterDTO.getUserPhone());
        unAllowedUser.setUserEmail(unAllowedUserRegisterDTO.getUserEmail());
        unAllowedUser.setUserRegisterDate(now);

        return unAllowedUserRepository.save(unAllowedUser);
    }



    @Override
    public boolean unAllowedUserApprove(UnAllowedUserApproveDTO unAllowedUserApproveDTO) {
        UnAllowedUser deletedUser=null;
        AllowedUser allowedUser=null;

        deletedUser=unAllowedUserRepository.deleteByID(unAllowedUserApproveDTO.getUserID());
        if(deletedUser!=null) {
            allowedUser=new AllowedUser();
            allowedUser.setUserID(deletedUser.getUserID());
            allowedUser.setUserPW(deletedUser.getUserPW());
            allowedUser.setUserName(deletedUser.getUserName());
            allowedUser.setUserSex(deletedUser.getUserSex());
            allowedUser.setUserPhone(deletedUser.getUserPhone());
            allowedUser.setUserEmail(deletedUser.getUserEmail());
            allowedUser.setUserRegisterDate(deletedUser.getUserRegisterDate());
            LocalDateTime now = LocalDateTime.now();
            allowedUser.setAllowedUserApprovalDate(now);
            return allowedUserRepository.save(allowedUser);
        }
        return false;
    }

    @Override
    public boolean unAllowedUserUnApprove(UnAllowedUserApproveDTO unAllowedUserApproveDTO) {
        UnAllowedUser deletedUser=null;
        deletedUser=unAllowedUserRepository.deleteByID(unAllowedUserApproveDTO.getUserID());
        if(deletedUser!=null) {
            return true;
        }
        return false;
    }

    @Override
    public List<UnAllowedUser> unAllowedUserReadAll() {
        return unAllowedUserRepository.unAllowedUserReadAll();
    }

    @Override
    public List<UnAllowedUser> unAllowedUserReadByID(UnAllowedUserReadByIDDTO unAllowedUserReadByIDDTO) {
        UnAllowedUser unAllowedUser = new UnAllowedUser();
        unAllowedUser.setUserID(unAllowedUserReadByIDDTO.getUserID());

        return unAllowedUserRepository.unAllowedUserReadByID(unAllowedUser);
    }

    @Override
    public List<UnAllowedUser> unAllowedUserReadByName(UnAllowedUserReadByNameDTO unAllowedUserReadByNameDTO) {
        UnAllowedUser unAllowedUser = new UnAllowedUser();
        unAllowedUser.setUserName(unAllowedUserReadByNameDTO.getUserName());

        return unAllowedUserRepository.unAllowedUserReadByName(unAllowedUser);
    }



}