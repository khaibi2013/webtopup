package com.example.web_top_up.service;

import com.example.web_top_up.model.DepartmentReponse;
import com.example.web_top_up.model.IdeaReponse;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.form.DepartmentForm;
import com.example.web_top_up.repositories.DepartmentRepository;

import java.util.List;

public interface DepartmentService {


    DepartmentEntity createDepartment(DepartmentForm sectorForm);

    List<DepartmentEntity> findAllDepartment();

    DepartmentEntity findById(Long id);

    void deleteDepartment(Long id);

    DepartmentReponse countDepartment(Long id);

    List<IdeaReponse> findIdeaInDepartment(Long id);


}
