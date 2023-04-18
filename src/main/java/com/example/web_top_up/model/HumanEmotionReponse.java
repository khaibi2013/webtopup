package com.example.web_top_up.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HumanEmotionReponse {


    private Integer likeCount;

    private Integer dislikeCount;


}
