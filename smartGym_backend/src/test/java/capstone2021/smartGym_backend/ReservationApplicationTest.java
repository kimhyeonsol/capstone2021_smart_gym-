package capstone2021.smartGym_backend;

import capstone2021.smartGym_backend.service.ReservationService;
import capstone2021.smartGym_backend.service.UnAllowedUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

public class ReservationApplicationTest extends SmartGymBackendApplicationTests{
    @Autowired
    ReservationService reservationService;


    @Test
    @Commit
    void 예약가능시간테스트() {
        reservationService.calAvailableDate();
    }
}
