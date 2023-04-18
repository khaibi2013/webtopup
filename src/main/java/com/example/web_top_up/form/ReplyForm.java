package com.example.web_top_up.form;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class ReplyForm {

    private String content;

    private Integer commentId;

    private LocalDateTime createAt;

    private String email;

    private String image;
}
