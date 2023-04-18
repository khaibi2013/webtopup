package com.example.web_top_up.service.Impl;

import com.example.web_top_up.model.DepartmentData;
import com.example.web_top_up.model.DepartmentReponse;
import com.example.web_top_up.model.IdeaData;
import com.example.web_top_up.model.IdeaReponse;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.form.DepartmentForm;
import com.example.web_top_up.repositories.DepartmentRepository;
import com.example.web_top_up.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;



    @Override
    public DepartmentEntity createDepartment(DepartmentForm sectorForm) {
        DepartmentEntity entity = new DepartmentEntity();
        if (sectorForm.getId() != null){
            entity.setId(sectorForm.getId());
            entity.setNameDepartment(sectorForm.getNameDepartment());
            return departmentRepository.save(entity);
        }else {
            entity.setNameDepartment(sectorForm.getNameDepartment());
            return departmentRepository.save(entity);
        }

    }

    @Override
    public List<DepartmentEntity> findAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity findById(Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentReponse countDepartment(Long id) {
        DepartmentData departmentData =  departmentRepository.countDepartmentEntitiesById(id);
        return DepartmentReponse.builder().countDepartment(departmentData.getCountDepartment()).build();
    }

    @Override
    public List<IdeaReponse> findIdeaInDepartment(Long id) {

        List<IdeaData> ideaData = departmentRepository.findByUserAndIdea(id);

        return ideaData.stream().map(
                data ->
                new IdeaReponse(data.getId(), data.getTitle(), data.getCreateAt())).collect(Collectors.toList());
    }


}
