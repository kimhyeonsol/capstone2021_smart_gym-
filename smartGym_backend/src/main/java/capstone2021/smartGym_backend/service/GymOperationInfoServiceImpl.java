package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.GymInfo.GymHolidayCreateDeleteDTO;
import capstone2021.smartGym_backend.DTO.GymInfo.GymOperationInfoDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.GymOperationInfo;
import capstone2021.smartGym_backend.repository.GymOperationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class GymOperationInfoServiceImpl implements GymOperationInfoService{
    private final GymOperationInfoRepository gymOperationInfoRepository;

    @Autowired
    public GymOperationInfoServiceImpl(GymOperationInfoRepository gymOperationInfoRepository) {
        this.gymOperationInfoRepository = gymOperationInfoRepository;
    }

    @Override
    public int update(GymOperationInfoDTO gymOperationInfoDTO) {
        if(gymOperationInfoDTO.getGymOperationInfoReservationDuration() == null || gymOperationInfoDTO.getGymOperationInfoReservationDuration().isBlank()) {
            return 1;
        }
        if(gymOperationInfoDTO.getGymOperationInfoOperatingStartTime() == null || gymOperationInfoDTO.getGymOperationInfoOperatingStartTime().isBlank()) {
            return 1;
        }
        if(gymOperationInfoDTO.getGymOperationInfoOperatingEndTime() == null || gymOperationInfoDTO.getGymOperationInfoOperatingEndTime().isBlank()) {
            return 1;
        }

        if(gymOperationInfoDTO.getGymOperationInfoRegularHoliday().equals("월화수목금토일")){ //모든 요일 정기휴무로 지정할 시 false
            return 3;
        }
        if(gymOperationInfoDTO.getGymOperationInfoOperatingStartTime().equals(gymOperationInfoDTO.getGymOperationInfoOperatingEndTime())){ //운영 시작시간과 종료시간이 같을 시 false
            return 2;
        }

        GymOperationInfo gymOperationInfo = new GymOperationInfo();
        gymOperationInfo.setGymOperationInfoReservationDuration(gymOperationInfoDTO.getGymOperationInfoReservationDuration());
        gymOperationInfo.setGymOperationInfoRegularHoliday(gymOperationInfoDTO.getGymOperationInfoRegularHoliday());

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            gymOperationInfo.setGymOperationInfoOperatingStartTime(format.parse(gymOperationInfoDTO.getGymOperationInfoOperatingStartTime()));
            gymOperationInfo.setGymOperationInfoOperatingEndTime(format.parse(gymOperationInfoDTO.getGymOperationInfoOperatingEndTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return gymOperationInfoRepository.update(gymOperationInfo);
    }

    @Override
    public GymOperationInfoDTO read() {
        return gymOperationInfoRepository.read();
    }

    @Override
    public boolean createHoliday(GymHolidayCreateDeleteDTO gymHolidayCreateDeleteDTO) {
        if(gymHolidayCreateDeleteDTO.getGymHolidayDate() == null || gymHolidayCreateDeleteDTO.getGymHolidayDate().isBlank()){
            return false;
        }

        GymHoliday gymHoliday = new GymHoliday();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            gymHoliday.setGymHolidayDate(format.parse(gymHolidayCreateDeleteDTO.getGymHolidayDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return gymOperationInfoRepository.createHoliday(gymHoliday);
    }

    @Override
    public boolean deleteHoliday(GymHolidayCreateDeleteDTO gymHolidayCreateDeleteDTO) {
        if(gymHolidayCreateDeleteDTO.getGymHolidayDate() == null || gymHolidayCreateDeleteDTO.getGymHolidayDate().isBlank()){
            return false;
        }

        GymHoliday gymHoliday = new GymHoliday();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            gymHoliday.setGymHolidayDate(format.parse(gymHolidayCreateDeleteDTO.getGymHolidayDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return gymOperationInfoRepository.deleteHoliday(gymHoliday);
    }

    @Override
    public List<GymHoliday> readHoliday() {
        return gymOperationInfoRepository.readHoliday();
    }
}
