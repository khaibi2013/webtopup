package com.example.web_top_up.form;


import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class HumanEmotionForm {



    private Integer likes;

    private Integer dislike;

    private Integer isActive;

    private String email;

}
