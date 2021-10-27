package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.GymInfo.GymInfoDTO;
import capstone2021.smartGym_backend.domain.GymInfo;
import capstone2021.smartGym_backend.repository.GymInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GymInfoServiceImpl implements GymInfoService{
    private final GymInfoRepository gymInfoRepository;

    @Autowired
    public GymInfoServiceImpl(GymInfoRepository gymInfoRepository) {
        this.gymInfoRepository = gymInfoRepository;
    }

    @Override
    public boolean update(GymInfoDTO gymInfoDTO) {
        GymInfo gymInfo = new GymInfo();
        gymInfo.setGymInfoName(gymInfoDTO.getGymInfoName());
        gymInfo.setGymInfoAddress(gymInfoDTO.getGymInfoAddress());
        gymInfo.setGymInfoPhoneNumber(gymInfoDTO.getGymInfoPhoneNumber());
        gymInfo.setGymInfoEquipmentLayout(gymInfoDTO.getGymInfoEquipmentLayout());

        return gymInfoRepository.update(gymInfo);
    }

    @Override
    public GymInfo read() {
        return gymInfoRepository.read();
    }
}
