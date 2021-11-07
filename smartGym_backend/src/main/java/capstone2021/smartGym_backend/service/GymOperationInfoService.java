package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.GymInfo.GymHolidayCreateDeleteDTO;
import capstone2021.smartGym_backend.DTO.GymInfo.GymOperationInfoDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;

import java.util.List;

public interface GymOperationInfoService {
    int update(GymOperationInfoDTO gymOperationInfoDTO); //헬스장 운영정보 수정
    GymOperationInfoDTO read(); //헬스장 운영정보 조회
    boolean createHoliday(GymHolidayCreateDeleteDTO gymHolidayCreateDeleteDTO); //헬스장 운영정보 휴무일 등록
    boolean deleteHoliday(GymHolidayCreateDeleteDTO gymHolidayCreateDeleteDTO); //헬스장 운영정보 휴무일 삭제
    List<GymHoliday> readHoliday(); //헬스장 운영정보 휴무일 조회
}
