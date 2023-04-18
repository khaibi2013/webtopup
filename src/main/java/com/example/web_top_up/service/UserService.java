package com.example.web_top_up.service;

import com.example.web_top_up.model.entities.UserEntity;
import com.example.web_top_up.form.UserForm;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserForm userForm);

    List<UserEntity> findAllUser();

    void deleteUser(Long id);

    UserEntity findByEmail(String email);

}
