package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.Equipment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DBEquipmentRepository implements EquipmentRepository{
    private final EntityManager em;

    public DBEquipmentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Equipment saveEquipment(Equipment equipment) {
        em.persist(equipment);
        return equipment;
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        em.merge(equipment);
        return equipment;
    }

    @Override
    public Equipment deleteEquipment(Equipment equipment) {
        if (em.contains(equipment)) {
            em.remove(equipment);
        } else {
            em.remove(em.merge(equipment));
        }

        return equipment;
    }
}
