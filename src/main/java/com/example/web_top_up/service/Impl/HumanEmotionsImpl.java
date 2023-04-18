package com.example.web_top_up.service.Impl;

import com.example.web_top_up.domain.Variable;
import com.example.web_top_up.model.HumanEmotionData;
import com.example.web_top_up.model.HumanEmotionReponse;
import com.example.web_top_up.model.TotalLikeAndDislike;
import com.example.web_top_up.model.entities.HumanEmotionsEntity;
import com.example.web_top_up.repositories.HumanEmotionReponsitory;
import com.example.web_top_up.repositories.IdeaRepository;
import com.example.web_top_up.service.HumanEmotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HumanEmotionsImpl implements HumanEmotionsService {

    @Autowired
    private HumanEmotionReponsitory humanEmotionReponsitory;
    @Autowired
    private IdeaRepository ideaRepository;

    @Override
    public HumanEmotionReponse findLikes(Long id) {
//        HumanEmotionsEntity humanEmotionsEntity = new HumanEmotionsEntity();
        HumanEmotionData humanEmotions = humanEmotionReponsitory.findContLike(id, Variable.likes);
        return HumanEmotionReponse.builder().likeCount(humanEmotions.getLikeCount()).build();
    }

    @Override
    public void createLike(Long id) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("Test "+ email);
        HumanEmotionsEntity humanEmotionsEntity = new HumanEmotionsEntity();

        HumanEmotionsEntity dislike = humanEmotionReponsitory.findByEmailAndAndIdea(email,id, Variable.dislike);
        if (dislike !=null){
            humanEmotionsEntity.setId(dislike.getId());
            humanEmotionsEntity.setLikes(Variable.likes);
            humanEmotionsEntity.setDislike(null);
            humanEmotionsEntity.setCreateAt(LocalDateTime.now());
            humanEmotionsEntity.setEmail(email);
            humanEmotionsEntity.setIdea(ideaRepository.findById(id).orElseThrow());
            humanEmotionReponsitory.save(humanEmotionsEntity);
        }else {
            HumanEmotionsEntity humanEmotion = humanEmotionReponsitory.findByEmailAndAndIdea(email,id, Variable.likes);
            if (humanEmotion == null){

                humanEmotionsEntity.setLikes(Variable.likes);
                humanEmotionsEntity.setEmail(email);
                humanEmotionsEntity.setCreateAt(LocalDateTime.now());
                humanEmotionsEntity.setIdea(ideaRepository.findById(id).orElseThrow());
                humanEmotionReponsitory.save(humanEmotionsEntity);

            }else {
                humanEmotionReponsitory.deleteById(humanEmotion.getId());
            }

        }
    }

    @Override
    public HumanEmotionReponse findCountDislike(Long id) {
        HumanEmotionData humanEmotions = humanEmotionReponsitory.findContDislike(id, Variable.dislike);
        return HumanEmotionReponse.builder().dislikeCount(humanEmotions.getDislikeCount()).build();
    }



    @Override
    public void createDislike(Long id) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        HumanEmotionsEntity humanEmotionsEntity = new HumanEmotionsEntity();

        HumanEmotionsEntity likes = humanEmotionReponsitory.findByEmailAndAndIdea(email,id, Variable.likes);
        if (likes !=null){
            humanEmotionsEntity.setId(likes.getId());
            humanEmotionsEntity.setLikes(null);
            humanEmotionsEntity.setDislike(Variable.dislike);
            humanEmotionsEntity.setEmail(email);
            humanEmotionsEntity.setCreateAt(LocalDateTime.now());
            humanEmotionsEntity.setIdea(ideaRepository.findById(id).orElseThrow());
            humanEmotionReponsitory.save(humanEmotionsEntity);
        }else {
            HumanEmotionsEntity humanEmotion = humanEmotionReponsitory.findByEmailAndAndIdea(email,id, Variable.dislike);

            if (humanEmotion == null){
                humanEmotionsEntity.setDislike(Variable.dislike);
                humanEmotionsEntity.setEmail(email);
                humanEmotionsEntity.setCreateAt(LocalDateTime.now());
                humanEmotionsEntity.setIdea(ideaRepository.findById(id).orElseThrow());
                humanEmotionReponsitory.save(humanEmotionsEntity);

            }else {
                humanEmotionReponsitory.deleteById(humanEmotion.getId());
            }
        }
    }

    @Override
    public TotalLikeAndDislike getCountsLike() {
        HumanEmotionData humanEmotions = humanEmotionReponsitory.CountLikeInMoth();
        if (humanEmotions == null){
            return TotalLikeAndDislike.builder().likesADis(0).build();
        }else {
            return TotalLikeAndDislike.builder().likesADis(humanEmotions.getLikeCount()).createAt(humanEmotions.getCreateAt()).build();
        }


    }

    @Override
    public TotalLikeAndDislike getCountDisLike() {
        HumanEmotionData humanEmotionData = humanEmotionReponsitory.CountDislikeInMoth();


        if (humanEmotionData == null){
            return TotalLikeAndDislike.builder().dislike(0).build();
        }else {
            return TotalLikeAndDislike.builder().dislike(humanEmotionData.getDislikeCount()).build();
        }
    }


}
