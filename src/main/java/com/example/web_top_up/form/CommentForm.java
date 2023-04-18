package com.example.web_top_up.form;

import com.example.web_top_up.model.entities.IdeaEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data

public class CommentForm {

    private Long id;
    @NotBlank(message = "Email cannot be empty or null")
    private String content;

    private Long idea;


    private LocalDateTime createAt;

    @NotBlank(message = "Email cannot be empty or null")
    private String email;

    private String image;
}
