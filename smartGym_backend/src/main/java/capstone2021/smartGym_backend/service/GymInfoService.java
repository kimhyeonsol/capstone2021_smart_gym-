package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.GymInfo.GymInfoDTO;
import capstone2021.smartGym_backend.domain.GymInfo;

import java.util.List;

public interface GymInfoService {
    boolean update(GymInfoDTO gymInfoDTO); //헬스장 정보 수정
    GymInfo read(); //헬스장 정보 조회
}
