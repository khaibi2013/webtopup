package com.example.web_top_up.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EventReponse {

    private String name;
    private Long counts;
    private String createAt;



}
