package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;
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
    public boolean createEquipment(Equipment equipment) {
        try{
            em.persist(equipment);
            return true;
        } catch (PersistenceException e){
            System.out.println("create 오류");
            return false;
        }
    }

    @Override
    public boolean updateEquipment(Equipment equipment) {
        try{
            em.merge(equipment);
            return true;
        } catch (PersistenceException e){
            System.out.println("update 오류");
            return false;
        }
    }

    @Override
    public boolean deleteEquipment(Equipment equipment) {
        try {
            if (em.contains(equipment)) {
                em.remove(equipment);
            } else {
                em.remove(em.merge(equipment));
            }
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("delete 오류");
            return false;
        }
    }

    @Override
    public List<Equipment> readAllEquipment() {
        return em.createQuery("select e from Equipment e", Equipment.class)
                .getResultList();

    }

    @Override
    public List<Equipment> readEquipment(String equipmentCategory) { //구현중
        if(equipmentCategory.equals("유산소")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%유산소%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("가슴")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%가슴%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("등")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%등%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("어깨")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%어깨%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("삼두")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%삼두%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("이두")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%이두%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("하체")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%하체%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("목")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%목%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("승모근")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%승모근%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("복부")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%복부%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("허리")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '%허리%'", Equipment.class)
                    .getResultList();
        }

        return null;
    }

    @Override
    public Equipment detailedReadEquipment(Equipment equipment) {
        return em.find(Equipment.class, equipment.getEquipmentID());
    }
}
