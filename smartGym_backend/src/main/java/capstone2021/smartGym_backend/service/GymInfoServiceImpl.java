package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.GymInfo.GymInfoDTO;
import capstone2021.smartGym_backend.DTO.GymInfo.GymInfoEquipmentLayoutDTO;
import capstone2021.smartGym_backend.domain.GymInfo;
import capstone2021.smartGym_backend.repository.GymInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URLDecoder;

@Service
@Transactional
public class GymInfoServiceImpl extends ImageService implements GymInfoService{
    private static final java.util.UUID UUID = null;
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

        return gymInfoRepository.update(gymInfo);
    }

    @Override
    public GymInfo read() {
        return gymInfoRepository.read();
    }

    @Override
    public boolean equipmentLayoutUpdate(GymInfoEquipmentLayoutDTO gymInfoEquipmentLayoutDTO) throws IOException {
        GymInfo findGymInfo = read();

        if(findGymInfo.getGymInfoEquipmentLayout().equals("")){
            String fileName = UUID.randomUUID() + "_" + gymInfoEquipmentLayoutDTO.getGymInfoEquipmentLayout().getOriginalFilename(); //S3에 이미지 업로드
            String fileUrl = upload(gymInfoEquipmentLayoutDTO.getGymInfoEquipmentLayout(), fileName,  "/");
            fileUrl = fileUrl.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com/%2F%2F", "https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//");
            findGymInfo.setGymInfoEquipmentLayout(fileUrl);
        }
        else {
            String oldFile = findGymInfo.getGymInfoEquipmentLayout();
            oldFile = URLDecoder.decode(oldFile.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//", ""), "UTF-8");
            deleteS3(oldFile);

            String fileName = UUID.randomUUID() + "_" + gymInfoEquipmentLayoutDTO.getGymInfoEquipmentLayout().getOriginalFilename(); //S3에 변경 후 이미지 업로드
            String fileUrl = upload(gymInfoEquipmentLayoutDTO.getGymInfoEquipmentLayout(), fileName,  "/");
            fileUrl = fileUrl.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com/%2F%2F", "https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//");
            findGymInfo.setGymInfoEquipmentLayout(fileUrl);
        }

        return gymInfoRepository.equipmentLayoutUpdate(findGymInfo);
    }

    @Override
    public String equipmentLayoutRead() {
        return gymInfoRepository.equipmentLayoutRead();
    }
}
