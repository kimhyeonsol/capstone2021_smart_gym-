package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.GymInfo;

public interface GymInfoRepository {
    boolean update(GymInfo gymInfo);
    GymInfo read();
    boolean equipmentLayoutUpdate(GymInfo gymInfo);
    String equipmentLayoutRead();
}
