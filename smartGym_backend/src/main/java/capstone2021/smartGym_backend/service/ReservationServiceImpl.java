package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.repository.GymOperationInfoRepository;
import capstone2021.smartGym_backend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final GymOperationInfoRepository gymOperationInfoRepository;

    @Autowired

    public ReservationServiceImpl(ReservationRepository reservationRepository,GymOperationInfoRepository gymOperationInfoRepository) {
        this.reservationRepository = reservationRepository;
        this.gymOperationInfoRepository=gymOperationInfoRepository;
    }


    @Override
    public List<String> calAvailableDate() {
        ArrayList<String> list=new ArrayList<String>();
        SimpleDateFormat sdformat = new SimpleDateFormat( "yyyy-MM-dd");
        int reservationDuration;

        Calendar cal=Calendar.getInstance();
        Date date=new Date(cal.getTimeInMillis());
        sdformat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        cal.setTime(date);

        reservationDuration=Integer.parseInt(gymOperationInfoRepository.readGymOperationInfoReservationDuration());

        for(int i=0;i<reservationDuration;i++) {
        list.add(sdformat.format(cal.getTime()));
        cal.add(Calendar.DATE,1);
        }
        return list;
    }
}
