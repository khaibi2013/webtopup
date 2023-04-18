package com.example.web_top_up.form;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserForm {




    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email cannot be empty or null")
    private String email;


    @NotBlank(message = "Email cannot be empty or null")
    private String password;


    @NotBlank(message = "Email cannot be empty or null")
    private String firstName;

    @NotBlank(message = "Email cannot be empty or null")
    private String lastName;

    @NotBlank(message = "Email cannot be empty or null")
    private String confirm;


    private Long departmentId;

    @NotBlank(message = "Email cannot be empty or null")
    private String role;


}
