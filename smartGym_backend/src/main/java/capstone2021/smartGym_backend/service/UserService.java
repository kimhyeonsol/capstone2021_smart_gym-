package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.User.UserDeleteDTO;
import capstone2021.smartGym_backend.DTO.User.UserUpdateDTO;


public interface UserService {

    boolean update(UserUpdateDTO userUpdateDTO); //회원 수정
    boolean delete(UserDeleteDTO userDeleteDTO); //회원 삭제
}
