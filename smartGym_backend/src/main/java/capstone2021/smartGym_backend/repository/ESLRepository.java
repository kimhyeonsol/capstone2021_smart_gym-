package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.DTO.Return.ReturnESLDetailedRead;
import capstone2021.smartGym_backend.domain.ESL;

import java.util.List;

public interface ESLRepository {
    int create(ESL esl);
    boolean update(ESL esl);
    boolean delete(ESL esl);
    List<ESL> read();
    ESL findByID(String id);
    ESL readByEquipmentID(Long equipmentID);
}

