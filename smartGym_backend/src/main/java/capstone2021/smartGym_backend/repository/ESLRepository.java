package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.ESL;

public interface ESLRepository {
    boolean save(ESL ESL);
    boolean update(ESL ESL);
    boolean delete(ESL ESL);
    ESL readByEquipmentID(Long equipmentID);
}

