package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.AllowedUser.*;
import capstone2021.smartGym_backend.DTO.User.UserDeleteDTO;
import capstone2021.smartGym_backend.DTO.User.UserUpdateDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.domain.User;
import capstone2021.smartGym_backend.repository.AllowedUserRepository;
import capstone2021.smartGym_backend.repository.UnAllowedUserRepository;
import capstone2021.smartGym_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional

public class AllowedUserServiceImpl extends UserServiceImpl implements AllowedUserService {
    AllowedUserRepository allowedUserRepository;
    UnAllowedUserRepository unAllowedUserRepository;
    UserRepository userRepository;

    @Autowired
    public AllowedUserServiceImpl(AllowedUserRepository allowedUserRepository, UnAllowedUserRepository unAllowedUserRepository,UserRepository userRepository) {
        super(userRepository);
        this.allowedUserRepository = allowedUserRepository;
        this.unAllowedUserRepository = unAllowedUserRepository;
        this.userRepository=userRepository;
    }

    @Override
    public int allowedUserLogin(AllowedUserLoginDTO allowedUserLoginDTO) {
        AllowedUser findAllowedUser = null;
        UnAllowedUser findUnAllowedUser = null;

        findAllowedUser = allowedUserRepository.findByAllowedUserID(allowedUserLoginDTO.getUserID());
        if (findAllowedUser != null) {
            if (findAllowedUser.getUserPW().equals(allowedUserLoginDTO.getUserPW())) {
                return 0;
            } else {
                return 1;
            }
        }

        findUnAllowedUser = unAllowedUserRepository.findByUnAllowedUserID(allowedUserLoginDTO.getUserID());
        if (findUnAllowedUser != null) {
            return 2;
        }
        return 3;

    }

    @Override
    public AllowedUserInfoDTO readUserInfo(AllowedUserReadUserInfoDTO allowedUserReadUserInfoDT) {
        AllowedUser findAllowedUser = null;

        findAllowedUser = allowedUserRepository.findByAllowedUserID(allowedUserReadUserInfoDT.getUserID());

        if (findAllowedUser != null) {
            AllowedUserInfoDTO allowedUserInfoDTO=new AllowedUserInfoDTO();
            allowedUserInfoDTO.setUserID(findAllowedUser.getUserID());
            allowedUserInfoDTO.setUserPW(findAllowedUser.getUserPW());
            allowedUserInfoDTO.setUserName(findAllowedUser.getUserName());
            allowedUserInfoDTO.setUserSex(findAllowedUser.getUserSex());
            allowedUserInfoDTO.setUserPhone(findAllowedUser.getUserPhone());
            allowedUserInfoDTO.setUserEmail(findAllowedUser.getUserEmail());
            allowedUserInfoDTO.setUserRegisterDate(findAllowedUser.getUserRegisterDate());
            allowedUserInfoDTO.setAllowedUserReservationAuthority(findAllowedUser.getAllowedUserReservationAuthority());
            allowedUserInfoDTO.setAllowedUserApprovalDate(findAllowedUser.getAllowedUserApprovalDate());
            return allowedUserInfoDTO;
        }
        return null;
    }

    @Override
    public String allowedUserFindID(AllowedUserFindIDDTO allowedUserFindIDDTO) {

        AllowedUser findAllowedUser = null;
        findAllowedUser = allowedUserRepository.findByAllowedUserName(allowedUserFindIDDTO.getUserName());

        if (findAllowedUser != null) {
            if (findAllowedUser.getUserEmail().equals(allowedUserFindIDDTO.getUserEmail())) {
                return findAllowedUser.getUserID();
            }
        }
        return null;
    }

    @Override
    public String allowedUserFindPW(AllowedUserFindPWDTO allowedUserFindPWDTO) {

        AllowedUser findAllowedUser = null;
        findAllowedUser = allowedUserRepository.findByAllowedUserID(allowedUserFindPWDTO.getUserID());
        if (findAllowedUser != null) {
            if (findAllowedUser.getUserEmail().equals(allowedUserFindPWDTO.getUserEmail())) {
                return findAllowedUser.getUserPW();
            }
        }
        return null;
    }

    @Override
    public boolean allowedUserReservationAuthorityUpdate(AllowedUserReservationAuthorityDTO allowedUserReservationAuthorityDTO) {
        AllowedUser findAllowedUser = allowedUserRepository.findByAllowedUserID(allowedUserReservationAuthorityDTO.getUserID());
        findAllowedUser.setAllowedUserReservationAuthority(allowedUserReservationAuthorityDTO.getAllowedUserReservationAuthority());

        return allowedUserRepository.allowedUserReservationAuthorityUpdate(findAllowedUser);
    }

    @Override
    public List<AllowedUser> allowedUserReadAll() {
        return allowedUserRepository.allowedUserReadAll();
    }

    @Override
    public List<AllowedUser> allowedUserReadByID(AllowedUserReadByIDDTO allowedUserReadByIDDTO) {
        AllowedUser allowedUser = new AllowedUser();
        allowedUser.setUserID(allowedUserReadByIDDTO.getUserID());

        return allowedUserRepository.allowedUserReadByID(allowedUser);
    }

    @Override
    public List<AllowedUser> allowedUserReadByName(AllowedUserReadByNameDTO allowedUserReadByNameDTO) {
        AllowedUser allowedUser = new AllowedUser();
        allowedUser.setUserName(allowedUserReadByNameDTO.getUserName());

        return allowedUserRepository.allowedUserReadByName(allowedUser);
    }

    @Override
    public AllowedUser deleteUser(AllowedUserDeleteDTO allowedUserDeleteDTO) {
        AllowedUser allowedUser = null;
        allowedUser=allowedUserRepository.findByAllowedUserID(allowedUserDeleteDTO.getUserID());
        allowedUser=allowedUserRepository.delete(allowedUser);
        return allowedUser;
    }

    @Override
    public AllowedUser update(AllowedUserUpdateDTO allowedUserUpdateDTO) {
        AllowedUser allowedUser=null;
        allowedUser=allowedUserRepository.update(allowedUserUpdateDTO);
        return allowedUser;
    }

}