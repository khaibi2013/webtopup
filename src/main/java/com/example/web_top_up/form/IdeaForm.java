package com.example.web_top_up.form;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class IdeaForm {

    private Long id;

    @NotBlank(message = "Email cannot be empty or null")
    @Min(value = 8)
    private String title;

    @NotBlank(message = "Email cannot be empty or null")
    private LocalDateTime createAt;

    private Integer incognito;

    private Long user;

    private Long event;


    private Long category;

    private String email;

    private String fileSubmissions;


}
