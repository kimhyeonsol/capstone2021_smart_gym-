package capstone2021.smartGym_backend.repository;

import capstone2021.smartGym_backend.domain.ESL;

import java.util.List;

public interface ESLRepository {
    int create(ESL esl);
    boolean update(ESL esl);
    boolean delete(ESL esl);
    boolean updateWhenEquipmentDelete(long id); //운동기구 삭제 시 ESL 매칭 해제
    List<ESL> read();
    ESL findByID(String id);
    ESL readByEquipmentID(Long equipmentID);
}

