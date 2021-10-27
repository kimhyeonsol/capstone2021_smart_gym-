package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.DTO.GymInfo.GymHolidayCreateDTO;
import capstone2021.smartGym_backend.DTO.GymInfo.GymHolidayDeleteDTO;
import capstone2021.smartGym_backend.DTO.GymInfo.GymOperationInfoDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.service.GymInfoService;
import capstone2021.smartGym_backend.service.GymOperationInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class GymInfoApplicationTests {
    @Autowired
    GymInfoService gymInfoService;
    @Autowired
    GymOperationInfoService gymOperationInfoService;

    @Test
    @Commit
    void create테스트() {
        /*GymOperationInfoDTO gymOperationInfoDTO = new GymOperationInfoDTO();
        gymOperationInfoDTO.setGymOperationInfoReservationDuration("3");
        gymOperationInfoDTO.setGymOperationInfoRegularHoliday("월");
        gymOperationInfoDTO.setGymOperationInfoOperatingStartTime("00:00");
        gymOperationInfoDTO.setGymOperationInfoOperatingEndTime("23:59");

        gymOperationInfoService.update(gymOperationInfoDTO);*/

        GymHolidayCreateDTO gymHolidayCreateDTO = new GymHolidayCreateDTO();
        gymHolidayCreateDTO.setGymHolidayDate("2021-10-26");

        gymOperationInfoService.createHoliday(gymHolidayCreateDTO);
    }

    @Test
    @Commit
    void delete테스트() {
        GymHolidayDeleteDTO gymHolidayDeleteDTO = new GymHolidayDeleteDTO();
        gymHolidayDeleteDTO.setGymHolidayID((long)1);

        gymOperationInfoService.deleteHoliday(gymHolidayDeleteDTO);
    }
}
