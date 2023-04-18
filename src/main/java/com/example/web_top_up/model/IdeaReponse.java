package com.example.web_top_up.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class IdeaReponse {

    private Long id;

    private String title;

    private String email;

    private LocalDateTime createAt;

    private Integer ideaId;

    private Integer counts;

    private String nameUser;

    private List<FileResponse> fileResponseList;

    public IdeaReponse(Long id, String title, LocalDateTime createAt) {
        this.id = id;
        this.title = title;
        this.createAt= createAt;
    }
}
