package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserEmailDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserIdDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserPhoneDuplDTO;
import capstone2021.smartGym_backend.DTO.UnAllowedUser.UnAllowedUserRegisterDTO;
import capstone2021.smartGym_backend.DTO.User.UserDeleteDTO;
import capstone2021.smartGym_backend.DTO.User.UserUpdateDTO;
import capstone2021.smartGym_backend.domain.UnAllowedUser;
import capstone2021.smartGym_backend.repository.UnAllowedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnAllowedUserServiceImpl implements UnAllowedUserService {

    UnAllowedUserRepository unAllowedUserRepository;


    @Autowired
    public UnAllowedUserServiceImpl(UnAllowedUserRepository unAllowedUserRepository) {
        this.unAllowedUserRepository=unAllowedUserRepository;
    }

    @Override
    public boolean unAllowedUserRegister(UnAllowedUserRegisterDTO unAllowedUserRegisterDTO)  {
        UnAllowedUser unAllowedUser = new UnAllowedUser();
        unAllowedUser.setUserID(unAllowedUserRegisterDTO.getUserID());
        unAllowedUser.setUserPW(unAllowedUserRegisterDTO.getUserPW());
        unAllowedUser.setUserName(unAllowedUserRegisterDTO.getUserName());
        unAllowedUser.setUserSex(unAllowedUserRegisterDTO.getUserSex());
        unAllowedUser.setUserPhone(unAllowedUserRegisterDTO.getUserPhone());
        unAllowedUser.setUserEmail(unAllowedUserRegisterDTO.getUserEmail());
        unAllowedUser.setUnAllowedUserApprovalAuthority("1");

        return unAllowedUserRepository.register(unAllowedUser);
    }


    @Override
    public boolean unAllowedUserIdDuplicateCheck(UnAllowedUserIdDuplDTO unAllowedUserIdDuplDTO){
        return unAllowedUserRepository.idDuplicateCheck(unAllowedUserIdDuplDTO.getUserID());
    }

    @Override
    public boolean unAllowedUserPhoneDuplicateCheck(UnAllowedUserPhoneDuplDTO unAllowedUserPhoneDuplDTO) {
        return unAllowedUserRepository.phoneDuplicateCheck(unAllowedUserPhoneDuplDTO.getUserPhone());
    }

    @Override
    public boolean unAllowedUserEmailDuplicateCheck(UnAllowedUserEmailDuplDTO unAllowedUserEmailDuplDTO) {
        return unAllowedUserRepository.emailDuplicateCheck(unAllowedUserEmailDuplDTO.getUserEmail());
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
