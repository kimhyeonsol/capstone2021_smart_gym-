package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Reservation.CalHolidayDateDTO;
import capstone2021.smartGym_backend.domain.GymHoliday;
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

    @Override
    public List<Integer> calRegularHolidayDate(CalHolidayDateDTO calHolidayDateDTO) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        int lastDayOfMonth;
        String regularHoliday;
        ArrayList<Integer> regularHolidayArray=new ArrayList<Integer>();

        Calendar cal=Calendar.getInstance();
        cal.set(calHolidayDateDTO.getYear(),calHolidayDateDTO.getMonth()-1,1);
        lastDayOfMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        regularHoliday=gymOperationInfoRepository.readRegularHoliday();

        for(int i=0;i<regularHoliday.length();i++){
            switch(regularHoliday.charAt(i)) {
                case '일':
                    regularHolidayArray.add(1);
                    break;
                case '월':
                    regularHolidayArray.add(2);
                    break;
                case '화':
                    regularHolidayArray.add(3);
                    break;
                case '수':
                    regularHolidayArray.add(4);
                    break;
                case '목':
                    regularHolidayArray.add(5);
                    break;
                case '금':
                    regularHolidayArray.add(6);
                    break;
                case '토':
                    regularHolidayArray.add(7);
                    break;
            }
        }

        for(int i=1;i<=lastDayOfMonth;i++) {
            cal.set(calHolidayDateDTO.getYear(),calHolidayDateDTO.getMonth()-1,i);
            for(int j=0;j<regularHoliday.length();j++){
                if(cal.get(Calendar.DAY_OF_WEEK)==regularHolidayArray.get(j)) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    @Override
    public List<Integer> calHolidayDate(CalHolidayDateDTO calHolidayDateDTO) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        List<GymHoliday> gymHolidayList=null;
        SimpleDateFormat sdformat = new SimpleDateFormat( "dd");

        Calendar cal=Calendar.getInstance();
        cal.set(calHolidayDateDTO.getYear(),calHolidayDateDTO.getMonth()-1,1);

        gymHolidayList= gymOperationInfoRepository.readHolidayByMonth(calHolidayDateDTO.getYear(),calHolidayDateDTO.getMonth(),cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        for(GymHoliday gm:gymHolidayList){
            list.add(Integer.parseInt(sdformat.format(gm.getGymHolidayDate())));
        }
        return list;
    }

}
