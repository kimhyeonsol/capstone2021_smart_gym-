package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.GymInfo.GymOperationInfoDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.GymOperationInfo;

import java.util.List;

public interface GymOperationInfoRepository {
    int update(GymOperationInfo gymOperationInfo);
    GymOperationInfoDTO read();
    boolean createHoliday(GymHoliday gymHoliday);
    boolean deleteHoliday(GymHoliday gymHoliday);
    List<GymHoliday> readHoliday();
    GymOperationInfo readGymOperationInfo();
    String readGymOperationInfoReservationDuration();
    String readRegularHoliday();
    List<GymHoliday> readHolidayByMonth(int year,int month,int lastOfMonth);
}
