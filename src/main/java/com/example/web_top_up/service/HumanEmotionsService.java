package com.example.web_top_up.service;

import com.example.web_top_up.model.HumanEmotionReponse;
import com.example.web_top_up.model.TotalLikeAndDislike;

public interface HumanEmotionsService {

    HumanEmotionReponse findLikes(Long id);

    void createLike(Long id);

    HumanEmotionReponse findCountDislike(Long id);

    void createDislike(Long id);

    TotalLikeAndDislike getCountsLike();

    TotalLikeAndDislike getCountDisLike();




}
