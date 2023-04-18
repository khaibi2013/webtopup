package com.example.web_top_up.form;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class EventForm {
    private Long id;



    @NotBlank(message = "Email cannot be empty or null")
    @Min(value = 8 , message = "characters must be greater than 8")
    private String nameEvent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    private String dueTime;



    private LocalDateTime createAt;

}
