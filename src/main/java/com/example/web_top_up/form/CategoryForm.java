package com.example.web_top_up.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryForm {

    private Long id;

    @NotBlank(message = "Email cannot be empty or null")
    private String nameCategory;

}
