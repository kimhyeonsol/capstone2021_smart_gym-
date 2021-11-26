package capstone2021.smartGym_backend.service;

import capstone2021.smartGym_backend.DTO.Equipment.EquipmentSearchByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Reservation.*;
import capstone2021.smartGym_backend.DTO.Return.ReturnReservationReadByEquipmentDTO;
import capstone2021.smartGym_backend.domain.AllowedUser;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.GymHoliday;
import capstone2021.smartGym_backend.domain.Reservation;
import capstone2021.smartGym_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@EnableScheduling
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final GymOperationInfoRepository gymOperationInfoRepository;
    private final EquipmentRepository equipmentRepository;
    private final AllowedUserRepository allowedUserRepository;
    private final GymInfoRepository gymInfoRepository;

    @Autowired

    public ReservationServiceImpl(ReservationRepository reservationRepository,GymOperationInfoRepository gymOperationInfoRepository,EquipmentRepository equipmentRepository,AllowedUserRepository allowedUserRepository,GymInfoRepository gymInfoRepository) {
        this.reservationRepository = reservationRepository;
        this.gymOperationInfoRepository=gymOperationInfoRepository;
        this.equipmentRepository=equipmentRepository;
        this.allowedUserRepository=allowedUserRepository;
        this.gymInfoRepository=gymInfoRepository;
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
        List<Reservation> reservationList=null;

        //예약하는 사용자 객체 찾기
        AllowedUser allowedUser= allowedUserRepository.findByAllowedUserID(reservationCreateDTO.getUserID());

        //만약 사용자에게 예약 권한이 없다면 1반환
        if(allowedUser.getAllowedUserReservationAuthority().equals("X")) return 1;
        //만약 사용자 정보가 없다면 2반환
        if(allowedUser==null) return 2;

        reservation.setUserID(allowedUser);

        //만약 운동기구 정보가 없다면 3 반환
        Equipment equipment=equipmentRepository.findByID(reservationCreateDTO.getEquipmentID());
        if(equipment==null) return 3;

        //만약 운동기구 예약이 겹치는 시간이면 4 반환
        reservationList=reservationRepository.reservationDuplCheck(reservationCreateDTO);
        if(!reservationList.isEmpty())
            return 4;


        reservation.setEquipmentID(equipment);
        reservation.setStartTime(reservationCreateDTO.getStartTime());
        reservation.setEndTime(reservationCreateDTO.getEndTime());

        reservationRepository.reservationCreate(reservation);

        //예약 됐으면
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
            selectedDayReservationDTO.setEquipmentImage(r.getEquipmentID().getEquipmentImage());
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

    @Override
    public List<Reservation> equipmentIsinUseCurrently(Long equipmentID) {
        List<Reservation> list= reservationRepository.isInUse(equipmentID);
        return list;
    }

    @Scheduled(fixedDelay = 1000)//1분마다 체크
    @Override
    public void equipmentAvailableCheck() {
        List<Equipment> equipmentList;
        List<Reservation> list;
        equipmentList=equipmentRepository.readAll(0);
        int equipmentInUseCnt=0;
        float congestion;

        for(Equipment e:equipmentList){
            if(e.getEquipmentAvailable()==0) continue;
            else{
                list=equipmentIsinUseCurrently(e.getEquipmentID());
                if(!list.isEmpty()){
                    e.setEquipmentAvailable(1);
                    equipmentRepository.update(e);
                    equipmentInUseCnt++;
                    continue;
                }
                else{
                    e.setEquipmentAvailable(2);
                    equipmentRepository.update(e);
                    continue;
                }
            }
        }

        congestion=(float)equipmentInUseCnt/equipmentList.size()*100;
        String num=String.format("%.5f",congestion);
        congestion=Float.parseFloat(num);
        if(gymInfoRepository.read().getGymInfoCongestion()!=congestion) {
            gymInfoRepository.updateCongestion(congestion);
        }
    }


}
