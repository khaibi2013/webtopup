package com.example.web_top_up.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalLikeAndDislike {

    private Integer likesADis;

    private Integer dislike;

    private Integer countIdea;

    private String createAt;
}
