package com.example.web_top_up.service.Impl;


import com.example.web_top_up.model.entities.UserEntity;
import com.example.web_top_up.form.UserForm;
import com.example.web_top_up.repositories.DepartmentRepository;
import com.example.web_top_up.repositories.UserReponsitory;
import com.example.web_top_up.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserIpml implements UserService {


    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public UserEntity saveUser(UserForm userForm) {

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userForm.getEmail());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setRole(userForm.getRole());
        userEntity.setDepartment(departmentRepository.findById(userForm.getDepartmentId()).orElseThrow());
        userEntity.setFirstName(userForm.getFirstName());
        userEntity.setLastName(userForm.getLastName());

        return userReponsitory.save(userEntity);
    }

    @Override
    public List<UserEntity> findAllUser() {
        return userReponsitory.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userReponsitory.deleteById(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userReponsitory.findByEmail(email);
    }
}
