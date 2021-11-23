package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.GymInfo;

public interface GymInfoRepository {
    int update(GymInfo gymInfo);
    void updateCongestion(float equipmentInUseCnt);

    GymInfo read();
    boolean equipmentLayoutUpdate(GymInfo gymInfo);
    String equipmentLayoutRead();
}
