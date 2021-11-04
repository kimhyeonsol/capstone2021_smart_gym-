package capstone2021.smartGym_backend.service;


import capstone2021.smartGym_backend.DTO.Equipment.EquipmentCreateDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentDeleteDetailedReadDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentReadByCategoryDTO;
import capstone2021.smartGym_backend.DTO.Equipment.EquipmentUpdateDTO;
import capstone2021.smartGym_backend.domain.Equipment;
import capstone2021.smartGym_backend.domain.EquipmentCategory;
import capstone2021.smartGym_backend.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl extends ImageService implements EquipmentService{
    private static final java.util.UUID UUID = null;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public int create(EquipmentCreateDTO equipmentCreateDTO) throws IOException {
        if(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentName() == null || equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentName().isBlank()) {
            return 1;
        }
        if(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentNameNth() == null || equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentNameNth().isBlank()) {
            return 1;
        }
        if(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentCategoryList() == null || equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentCategoryList().isBlank()) {
            return 1;
        }
        if(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentAvailable() == -1) {
            return 1;
        }
        if(equipmentCreateDTO.getEquipmentImage().isEmpty()){
            return 1;
        }

        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentName());
        equipment.setEquipmentNameNth(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentNameNth());
        equipment.setEquipmentCategoryList(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentCategoryList());
        equipment.setEquipmentAvailable(equipmentCreateDTO.getEquipmentInfoCreateDTO().getEquipmentAvailable());

        String fileName = UUID.randomUUID() + "_" + equipmentCreateDTO.getEquipmentImage().getOriginalFilename(); //S3에 이미지 업로드
        String fileUrl = upload(equipmentCreateDTO.getEquipmentImage(), fileName,  "/");
        fileUrl = fileUrl.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com/%2F%2F", "https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//");
        equipment.setEquipmentImage(fileUrl);

        return equipmentRepository.create(equipment);
    }

    @Override
    public int update(EquipmentUpdateDTO equipmentUpdateDTO) throws IOException {
        if(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentName() == null || equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentName().isBlank()) {
            return 1;
        }
        if(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentNameNth() == null || equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentNameNth().isBlank()) {
            return 1;
        }
        if(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentCategoryList() == null || equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentCategoryList().isBlank()) {
            return 1;
        }
        if(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentAvailable() == -1) {
            return 1;
        }

        Equipment findEquipment = findByID(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentID());

        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentID());
        equipment.setEquipmentName(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentName());
        equipment.setEquipmentNameNth(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentNameNth());
        equipment.setEquipmentCategoryList(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentCategoryList());
        equipment.setEquipmentAvailable(equipmentUpdateDTO.getEquipmentInfoUpdateDTO().getEquipmentAvailable());
        equipment.setEquipmentImage(findEquipment.getEquipmentImage());

        if(equipmentUpdateDTO.getEquipmentImage() != null) { //이미지를 수정할 경우
            String oldFile = findEquipment.getEquipmentImage(); //S3에서 변경 전 이미지 삭제
            oldFile = URLDecoder.decode(oldFile.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//", ""), "UTF-8");
            deleteS3(oldFile);

            String fileName = UUID.randomUUID() + "_" + equipmentUpdateDTO.getEquipmentImage().getOriginalFilename(); //S3에 변경 후 이미지 업로드
            String fileUrl = upload(equipmentUpdateDTO.getEquipmentImage(), fileName,  "/");
            fileUrl = fileUrl.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com/%2F%2F", "https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//");
            equipment.setEquipmentImage(fileUrl);
        }

        return equipmentRepository.update(equipment);
    }

    @Override
    public boolean delete(EquipmentDeleteDetailedReadDTO equipmentDeleteDetailedReadDTO) throws IOException {
        if(equipmentDeleteDetailedReadDTO.getEquipmentID() == null) {
            return false;
        }

        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentDeleteDetailedReadDTO.getEquipmentID());

        Equipment findEquipment = findByID(equipmentDeleteDetailedReadDTO.getEquipmentID()); //S3에서 이미지 삭제
        String oldFile = findEquipment.getEquipmentImage();
        oldFile = URLDecoder.decode(oldFile.replace("https://smartgym-bucket.s3.ap-northeast-2.amazonaws.com//", ""), "UTF-8");
        deleteS3(oldFile);

        return equipmentRepository.delete(equipment);
    }

    @Override
    public Equipment findByID(long id) {
        return equipmentRepository.findByID(id);
    }

    @Override
    public List<Equipment> readAll() {
        return equipmentRepository.readAll();
    }

    @Override
    public List<Equipment> readByCategory(EquipmentReadByCategoryDTO equipmentReadByCategoryDTO){
        if(equipmentReadByCategoryDTO.getEquipmentCategorySelect() == null || equipmentReadByCategoryDTO.getEquipmentCategorySelect().isBlank()){
            return null;
        }

        String equipmentCategorySelect = equipmentReadByCategoryDTO.getEquipmentCategorySelect();

        return equipmentRepository.readByCategory(equipmentCategorySelect);
    }

    @Override
    public List<EquipmentCategory> detailedRead(EquipmentDeleteDetailedReadDTO equipmentdetailedReadDTO) {
        if(equipmentdetailedReadDTO.getEquipmentID() == null){
            return null;
        }

        Equipment equipment = new Equipment();
        equipment.setEquipmentID(equipmentdetailedReadDTO.getEquipmentID());

        return equipmentRepository.detailedRead(equipment);
    }
}
