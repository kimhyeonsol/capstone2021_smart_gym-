package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.ESL;

import java.util.List;

public interface ESLRepository {
    boolean create(ESL ESL);
    boolean update(ESL ESL);
    boolean delete(ESL ESL);
    List<ESL> read();
    ESL findByID(long id);
    ESL readByEquipmentID(Long equipmentID);
}

