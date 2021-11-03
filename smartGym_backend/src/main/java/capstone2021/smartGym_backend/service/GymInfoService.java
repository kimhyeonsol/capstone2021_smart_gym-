package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.GymInfo.GymInfoDTO;
import capstone2021.smartGym_backend.DTO.GymInfo.GymInfoEquipmentLayoutDTO;
import capstone2021.smartGym_backend.domain.GymInfo;

import java.io.IOException;

public interface GymInfoService {
    boolean update(GymInfoDTO gymInfoDTO); //헬스장 정보 수정
    GymInfo read(); //헬스장 정보 조회
    boolean equipmentLayoutUpdate(GymInfoEquipmentLayoutDTO gymInfoEquipmentLayoutDTO) throws IOException; //헬스장 운동기구 배치도 수정
    String equipmentLayoutRead();
}
