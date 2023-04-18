package com.example.web_top_up.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailForm {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;


}
