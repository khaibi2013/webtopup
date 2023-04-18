package com.example.web_top_up.model;

import java.time.LocalDateTime;

public interface IdeaData {


    Integer getIdeaId();

    Integer getCounts();

    Long getId();

    String getTitle();

    String getEmail();

    String getNameUser();

    LocalDateTime getCreateAt();
}
