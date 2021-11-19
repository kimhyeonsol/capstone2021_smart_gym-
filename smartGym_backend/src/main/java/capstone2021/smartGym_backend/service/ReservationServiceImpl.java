package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentSearchByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Reservation.*;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.repository.AllowedUserRepository;
import capstone2021.smartGym_backend.repository.EquipmentRepository;
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
    private final EquipmentRepository equipmentRepository;
    private final AllowedUserRepository allowedUserRepository;

    @Autowired

    public ReservationServiceImpl(ReservationRepository reservationRepository,GymOperationInfoRepository gymOperationInfoRepository,EquipmentRepository equipmentRepository,AllowedUserRepository allowedUserRepository) {
        this.reservationRepository = reservationRepository;
        this.gymOperationInfoRepository=gymOperationInfoRepository;
        this.equipmentRepository=equipmentRepository;
        this.allowedUserRepository=allowedUserRepository;
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
            if(!regularHolidayArray.isEmpty()) {
                for (int j = 0; j < regularHoliday.length(); j++) {
                    if (cal.get(Calendar.DAY_OF_WEEK) == regularHolidayArray.get(j)) {
                        list.add(i);
                    }
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

    @Override
    public EquipmentSearchByCategoryDTO searchEquipmentByCategory() {
        EquipmentSearchByCategoryDTO equipmentSearchByCategoryDTO=new EquipmentSearchByCategoryDTO();
        equipmentSearchByCategoryDTO.setChest(equipmentRepository.readByCategory("가슴"));
        equipmentSearchByCategoryDTO.setBack(equipmentRepository.readByCategory("등"));
        equipmentSearchByCategoryDTO.setNeck(equipmentRepository.readByCategory("목"));
        equipmentSearchByCategoryDTO.setStomach(equipmentRepository.readByCategory("복부"));
        equipmentSearchByCategoryDTO.setTriceps(equipmentRepository.readByCategory("삼두"));
        equipmentSearchByCategoryDTO.setTrapezius(equipmentRepository.readByCategory("승모근"));
        equipmentSearchByCategoryDTO.setShoulder(equipmentRepository.readByCategory("어깨"));
        equipmentSearchByCategoryDTO.setAerobic(equipmentRepository.readByCategory("유산소"));
        equipmentSearchByCategoryDTO.setBiceps(equipmentRepository.readByCategory("이두"));
        equipmentSearchByCategoryDTO.setLower_body(equipmentRepository.readByCategory("하체"));
        equipmentSearchByCategoryDTO.setWaist(equipmentRepository.readByCategory("허리"));
        equipmentSearchByCategoryDTO.setEtc(equipmentRepository.readByCategory("기타"));
        return equipmentSearchByCategoryDTO;
    }

    @Override
    public int makeReservation(ReservationCreateDTO reservationCreateDTO) {
        Reservation reservation= new Reservation();

        AllowedUser allowedUser= allowedUserRepository.findByAllowedUserID(reservationCreateDTO.getUserID());

        if(allowedUser.getAllowedUserReservationAuthority().equals("X")) return 1;
        if(allowedUser==null) return 2;
        reservation.setUserID(allowedUser);

        Equipment equipment=equipmentRepository.findByID(reservationCreateDTO.getEquipmentID());
        if(equipment==null) return 3;
        reservation.setEquipmentID(equipment);

        reservation.setStartTime(reservationCreateDTO.getStartTime());
        reservation.setEndTime(reservationCreateDTO.getEndTime());

        reservationRepository.reservationCreate(reservation);

        equipmentRepository.findByID(reservationCreateDTO.getEquipmentID()).setEquipmentAvailable(1);

        return 0;

    }

    @Override
    public List<SelectedDayReservationDTO> readMyReservationOfSelectedDay(ReservationReadSelectedDayDTO reservationReadSelectedDayDTO) {
        ArrayList<SelectedDayReservationDTO> list=new ArrayList<SelectedDayReservationDTO>();
        List<Reservation> reservationList=null;

        reservationList=reservationRepository.readReservationByUserIDAndDay(reservationReadSelectedDayDTO.getUserID(),reservationReadSelectedDayDTO.getYear(),reservationReadSelectedDayDTO.getMonth(),reservationReadSelectedDayDTO.getDay());

        for(Reservation r:reservationList){
            SelectedDayReservationDTO selectedDayReservationDTO=new SelectedDayReservationDTO();
            selectedDayReservationDTO.setReservationID(r.getReservationID());
            selectedDayReservationDTO.setEquipmentID(r.getEquipmentID().getEquipmentID());
            selectedDayReservationDTO.setEquipmentName(r.getEquipmentID().getEquipmentName());
            selectedDayReservationDTO.setEquipmentNameNth(r.getEquipmentID().getEquipmentNameNth());
            selectedDayReservationDTO.setStartTime(r.getStartTime());
            selectedDayReservationDTO.setEndTime(r.getEndTime());
            list.add(selectedDayReservationDTO);
        }

        return list;
    }

    @Override
    public List<SelectedDayReservationDTO> readEquipmentReservationOfSeletedDay(ReservationReadBySelectedDayAndEquipmentDTO reservationReadBySelectedDayAndEquipmentDTO) {
        ArrayList<SelectedDayReservationDTO> list=new ArrayList<SelectedDayReservationDTO>();
        List<Reservation> reservationList=null;

        reservationList=reservationRepository.readReservationByEquipmentAndDay(reservationReadBySelectedDayAndEquipmentDTO.getEquipmentID(),reservationReadBySelectedDayAndEquipmentDTO.getYear(),reservationReadBySelectedDayAndEquipmentDTO.getMonth(),reservationReadBySelectedDayAndEquipmentDTO.getDay());

        for(Reservation r:reservationList){
            SelectedDayReservationDTO selectedDayReservationDTO=new SelectedDayReservationDTO();
            selectedDayReservationDTO.setReservationID(r.getReservationID());
            selectedDayReservationDTO.setEquipmentID(r.getEquipmentID().getEquipmentID());
            selectedDayReservationDTO.setEquipmentName(r.getEquipmentID().getEquipmentName());
            selectedDayReservationDTO.setEquipmentNameNth(r.getEquipmentID().getEquipmentNameNth());
            selectedDayReservationDTO.setStartTime(r.getStartTime());
            selectedDayReservationDTO.setEndTime(r.getEndTime());
            list.add(selectedDayReservationDTO);
        }
        return list;
    }

    @Override
    public List<ReturnReservationReadByEquipmentDTO> reservationReadByEquipment(ReservationReadByEquipmentDTO reservationReadByEquipmentDTO) {
        return reservationRepository.reservationReadByEquipment(reservationReadByEquipmentDTO.getEquipmentID());
    }

    @Override
    public Boolean cancleReservation(ReservationCancleDTO reservationCancleDTO) {
        Reservation reservation=null;
        reservation=reservationRepository.findByID(reservationCancleDTO.getReservationID());
        equipmentRepository.findByID(reservation.getEquipmentID().getEquipmentID()).setEquipmentAvailable(2);
        return reservationRepository.delete(reservationCancleDTO.getReservationID());
    }

    @Override
    public ReservationReadOperatingTimeDTO readOperatingTime() {
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        ReservationReadOperatingTimeDTO reservationReadOperatingTimeDTO=new ReservationReadOperatingTimeDTO();
        reservationReadOperatingTimeDTO.setGymOperationInfoOperatingStartTime(format1.format(gymOperationInfoRepository.readGymOperationInfo().getGymOperationInfoOperatingStartTime()));
        reservationReadOperatingTimeDTO.setGymOperationInfoOperatingEndTime(format1.format(gymOperationInfoRepository.readGymOperationInfo().getGymOperationInfoOperatingEndTime()));
        return reservationReadOperatingTimeDTO;
    }
}
