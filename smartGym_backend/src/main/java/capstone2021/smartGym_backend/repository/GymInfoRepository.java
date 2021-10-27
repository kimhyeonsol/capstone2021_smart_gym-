package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.GymInfo;

import java.util.List;

public interface GymInfoRepository {
    boolean update(GymInfo gymInfo);
    GymInfo read();
}
