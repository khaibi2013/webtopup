package com.example.web_top_up.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class DepartmentForm {

    private Long id;

    @NotBlank(message = "Email cannot be empty or null")
    private String nameDepartment;

}
