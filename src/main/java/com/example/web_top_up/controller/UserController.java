package com.example.web_top_up.controller;


import com.example.web_top_up.form.UserForm;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.model.entities.UserEntity;
import com.example.web_top_up.service.DepartmentService;
import com.example.web_top_up.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
//@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/admin/register")
    public String userSignUp(Model model){

        List<DepartmentEntity> listDepartment = departmentService.findAllDepartment();
        model.addAttribute("departments", listDepartment);
        model.addAttribute("user", new UserForm());
        return "register";
    }

    @PostMapping("/createRegister")
    public String saveUserSignUp(@Valid @ModelAttribute("user") UserForm userForm, BindingResult bindingResult){
        System.out.println("test");

        if (bindingResult.hasErrors()){
            return "register";
        }

        UserEntity userEmail = userService.findByEmail(userForm.getEmail());
        if (userEmail != null){
            bindingResult.addError(new FieldError("user", "email","Email already used "));
            return "register";
        } else if (!userForm.getPassword().equals(userForm.getConfirm())) {
            bindingResult.addError(new FieldError("user", "password","same password"));
            return "register";
        }


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword= passwordEncoder.encode(userForm.getPassword());
        userForm.setPassword(encodePassword);
        userService.saveUser(userForm);
        return "redirect:/login";

    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model){
        String errorRole = "you have no right";
        model.addAttribute("msg",errorRole);
        return "haventRole";
    }



    @GetMapping ("/login")
    public String authentication(){
        return "login";
    }


    @GetMapping("admin/showUser")
    public String findAllUser(Model model){
        List<UserEntity> userList = userService.findAllUser();
        model.addAttribute("users", userList);
        return "showUser";
    }

    @GetMapping("admin/delete")
    public String deleteUser(@RequestParam("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/showUser";
    }

    @GetMapping("/testcode")
    public String testUser(){
        return "Testshow";
    }

}
