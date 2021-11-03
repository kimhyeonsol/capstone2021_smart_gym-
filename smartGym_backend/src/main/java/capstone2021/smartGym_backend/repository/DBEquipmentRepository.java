package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;
import capstone2021.smartGym_backend.domain.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class DBEquipmentRepository implements EquipmentRepository{
    private final EntityManager em;

    public DBEquipmentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean duplicateCheckEquipmentName(Long equipmentID, String equipmentName, String equipmentNameNth) { //중복일 경우 false
        List<Equipment> reduplicationEquipment;

        if(equipmentID == null){
            reduplicationEquipment = em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentName = :equipmentName AND e.equipmentNameNth = :equipmentNameNth", Equipment.class)
                    .setParameter("equipmentName", equipmentName).setParameter("equipmentNameNth", equipmentNameNth).getResultList();

            if(reduplicationEquipment.isEmpty()){
                return true;
            }
        }
        else{
            reduplicationEquipment = em.createQuery("SELECT e FROM Equipment e WHERE (e.equipmentName = :equipmentName AND e.equipmentNameNth = :equipmentNameNth) AND (e.equipmentID NOT IN (:equipmentID))", Equipment.class)
                    .setParameter("equipmentID", equipmentID).setParameter("equipmentName", equipmentName).setParameter("equipmentNameNth", equipmentNameNth).getResultList();

            if(reduplicationEquipment.isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void categorySettings(Equipment equipment, EquipmentCategory equipmentCategory) {
        if(equipment.getEquipmentCategoryList().contains("가슴")){
            equipmentCategory.setEquipmentCategoryChest(1);
        }
        if(equipment.getEquipmentCategoryList().contains("등")){
            equipmentCategory.setEquipmentCategoryBack(1);
        }
        if(equipment.getEquipmentCategoryList().contains("목")){
            equipmentCategory.setEquipmentCategoryNeck(1);
        }
        if(equipment.getEquipmentCategoryList().contains("복부")){
            equipmentCategory.setEquipmentCategoryStomach(1);
        }
        if(equipment.getEquipmentCategoryList().contains("삼두")){
            equipmentCategory.setEquipmentCategoryTriceps(1);
        }
        if(equipment.getEquipmentCategoryList().contains("승모근")){
            equipmentCategory.setEquipmentCategoryTrapezius(1);
        }
        if(equipment.getEquipmentCategoryList().contains("어깨")){
            equipmentCategory.setEquipmentCategoryShoulder(1);
        }
        if(equipment.getEquipmentCategoryList().contains("유산소")){
            equipmentCategory.setEquipmentCategoryAerobic(1);
        }
        if(equipment.getEquipmentCategoryList().contains("이두")){
            equipmentCategory.setEquipmentCategoryBiceps(1);
        }
        if(equipment.getEquipmentCategoryList().contains("하체")){
            equipmentCategory.setEquipmentCategoryLowerBody(1);
        }
        if(equipment.getEquipmentCategoryList().contains("허리")){
            equipmentCategory.setEquipmentCategoryWaist(1);
        }
        if(equipment.getEquipmentCategoryList().contains("기타")){
            equipmentCategory.setEquipmentCategoryEtc(1);
        }
    }

    @Override
    public boolean create(Equipment equipment) {
        boolean duplicateResult;

        duplicateResult = duplicateCheckEquipmentName(null, equipment.getEquipmentName(), equipment.getEquipmentNameNth());
        if(duplicateResult == true){
            try{
                em.persist(equipment);
                EquipmentCategory equipmentCategory = new EquipmentCategory();
                equipmentCategory.setEquipmentCategoryID(equipment);
                categorySettings(equipment, equipmentCategory);
                em.persist(equipmentCategory);

                return true;
            } catch (PersistenceException | IllegalStateException e){
                System.out.println("create 오류");
                return false;
            }
        }
        System.out.println("운동기구 이름 중복!");
        return false;
    }

    @Override
    public boolean update(Equipment equipment) {
        boolean duplicateResult;

        duplicateResult = duplicateCheckEquipmentName(equipment.getEquipmentID(), equipment.getEquipmentName(), equipment.getEquipmentNameNth());
        if(duplicateResult == true){
            try{
                em.merge(equipment);

                EquipmentCategory equipmentCategory = new EquipmentCategory();
                equipmentCategory.setEquipmentCategoryID(equipment);
                equipmentCategory.setEquipmentCategoryChest(0);
                equipmentCategory.setEquipmentCategoryBack(0);
                equipmentCategory.setEquipmentCategoryNeck(0);
                equipmentCategory.setEquipmentCategoryStomach(0);
                equipmentCategory.setEquipmentCategoryTriceps(0);
                equipmentCategory.setEquipmentCategoryTrapezius(0);
                equipmentCategory.setEquipmentCategoryShoulder(0);
                equipmentCategory.setEquipmentCategoryAerobic(0);
                equipmentCategory.setEquipmentCategoryBiceps(0);
                equipmentCategory.setEquipmentCategoryLowerBody(0);
                equipmentCategory.setEquipmentCategoryWaist(0);
                equipmentCategory.setEquipmentCategoryEtc(0);
                em.merge(equipmentCategory); // 카테고리 초기화

                categorySettings(equipment, equipmentCategory);

                em.merge(equipmentCategory);

                return true;

            } catch (PersistenceException | IllegalStateException e){
                System.out.println("update 오류");
                return false;
            }
        }
        System.out.println("운동기구 이름 중복!");
        return false;
    }

    @Override
    public boolean delete(Equipment equipment) {
        List<Reservation> reservations;
        List<EquipmentCategory> equipmentCategories;

        reservations = em.createQuery("SELECT r FROM Reservation r WHERE r.equipmentID IN (SELECT e.equipmentID FROM Equipment e WHERE r.equipmentID = :equipment)", Reservation.class)
                .setParameter("equipment", equipment).getResultList();

        equipmentCategories = em.createQuery("SELECT ec FROM EquipmentCategory ec WHERE ec.equipmentCategoryID IN (SELECT e.equipmentID FROM Equipment e WHERE ec.equipmentCategoryID = :equipment)", EquipmentCategory.class)
                .setParameter("equipment", equipment).getResultList();

        try {
            if (em.contains(equipment)) {
                for(Reservation reservation : reservations){ //예약 다 삭제
                    em.remove(reservation);
                }
                for(EquipmentCategory equipmentCategory : equipmentCategories){ //운동기구 카테고리 삭제
                    em.remove(equipmentCategory);
                }
                //ESL도 삭제??

                em.remove(equipment);
            } else {
                for(Reservation reservation : reservations){
                    em.remove(em.merge(reservation));
                }
                for(EquipmentCategory equipmentCategory : equipmentCategories){
                    em.remove(em.merge(equipmentCategory));
                }
                em.remove(em.merge(equipment));
            }
            return true;
        } catch (PersistenceException | IllegalStateException e){
            System.out.println("delete 오류");
            return false;
        }
    }

    @Override
    public Equipment findByID(long id) {
        return em.find(Equipment.class, id);
    }

    @Override
    public List<Equipment> readAll() {
        return em.createQuery("SELECT e FROM Equipment e", Equipment.class)
                .getResultList();
    }

    @Override
    public List<Equipment> readByCategory(String equipmentCategorySelect) {
        if(equipmentCategorySelect.equals("가슴")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryChest = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("등")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryBack = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("목")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryNeck = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("복부")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryStomach = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("삼두")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryTriceps = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("승모근")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryTrapezius = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("어깨")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryShoulder = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("유산소")) {
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryAerobic = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("이두")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryBiceps = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("하체")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryLowerBody = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("허리")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryWaist = 1)", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategorySelect.equals("기타")){
            return em.createQuery("SELECT e FROM Equipment e WHERE e.equipmentID IN (SELECT ec.equipmentCategoryID FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = e AND ec.equipmentCategoryEtc = 1)", Equipment.class)
                    .getResultList();
        }

        return null;
    }

    @Override
    public List<EquipmentCategory> detailedRead(Equipment equipment) {
        return em.createQuery("SELECT ec FROM EquipmentCategory ec WHERE ec.equipmentCategoryID = :equipment")
                .setParameter("equipment", equipment).getResultList();
    }
}
