package com.example.web_top_up.repositories;

import com.example.web_top_up.model.HumanEmotionData;
import com.example.web_top_up.model.entities.HumanEmotionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HumanEmotionReponsitory extends JpaRepository<HumanEmotionsEntity,Long> {

    @Query(value = "SELECT count(*) as likeCount FROM human_emotions hu where hu.idea_id = :id and hu.likes = :likes",nativeQuery = true)
    HumanEmotionData findContLike(Long id, Integer likes);

    @Query(value = "SELECT count(*) as dislikeCount FROM human_emotions hu where hu.idea_id = :id and hu.dislike = :dislike",nativeQuery = true)
    HumanEmotionData findContDislike(Long id, Integer dislike);

    @Query(value = "SELECT date_format(c.create_at, '%M %d %Y') as createAt, count(c.id) AS likeCount FROM web_top_up.human_emotions c where c.likes = 1 and MONTH(create_at) =  MONTH(CURDATE()) GROUP BY MONTH(CURDATE())  ",nativeQuery = true)
    HumanEmotionData CountLikeInMoth();

    @Query(value = "SELECT date_format(c.create_at, '%M %d %Y') as createAt, count(c.id) AS dislikeCount FROM web_top_up.human_emotions c where c.dislike = 0 and MONTH(create_at) =  MONTH(CURDATE()) GROUP BY MONTH(CURDATE()) ",nativeQuery = true)
    HumanEmotionData CountDislikeInMoth();



    @Query(value = "SELECT * FROM human_emotions hu where hu.email= :email and hu.idea_id = :ideaId and (hu.likes = :likesDis or hu.dislike = :likesDis)",nativeQuery = true)
    HumanEmotionsEntity findByEmailAndAndIdea(String email, Long ideaId, Integer likesDis);

    @Query(value = "delete from human_emotions hu where hu.id = :id",nativeQuery = true)
    HumanEmotionsEntity deleteHuman(Long id);




}
