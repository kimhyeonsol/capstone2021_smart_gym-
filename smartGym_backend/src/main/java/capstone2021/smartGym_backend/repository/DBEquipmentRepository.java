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
    public List<Equipment> readEquipment(String equipmentCategory) {
        if(equipmentCategory.equals("100000000000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("010000000000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '_1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("001000000000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '__1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000100000000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '___1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000010000000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '____1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000001000000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '_____1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000000100000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '______1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000000010000")) {
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '_______1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000000001000")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '________1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000000000100")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '_________1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000000000010")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '__________1%'", Equipment.class)
                    .getResultList();
        }

        else if(equipmentCategory.equals("000000000001")){
            return em.createQuery("select e from Equipment e where e.equipmentCategory like '___________1%'", Equipment.class)
                    .getResultList();
        }

        return null;
    }

    @Override
    public Equipment detailedReadEquipment(Equipment equipment) {
        return em.find(Equipment.class, equipment.getEquipmentID());
    }
}
