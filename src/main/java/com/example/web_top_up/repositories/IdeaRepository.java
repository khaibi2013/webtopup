package com.example.web_top_up.repositories;

import com.example.web_top_up.model.EventData;
import com.example.web_top_up.model.HumanEmotionData;
import com.example.web_top_up.model.IdeaData;
import com.example.web_top_up.model.entities.IdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdeaRepository extends JpaRepository<IdeaEntity,Long> {


    @Query(value = "SELECT * FROM idea_table i where i.event_id = :eventId", nativeQuery = true)
    List<IdeaEntity> findAllByEventId(long eventId);


    @Query(value = "SELECT * FROM idea_table i where i.category_id = :category", nativeQuery = true)
    List<IdeaEntity> findAllByCategoryId(long category);

    @Query(value = "SELECT ie.name_user as nameUser, hu.idea_id as ideaId, count(*) as counts, ie.id as id, ie.title as title, ie.email as email , ie.create_at as createAt  FROM idea_table ie  join human_emotions hu on hu.idea_id in (ie.id) where ie.event_id = :eventId group by hu.idea_id order by count(*) desc", nativeQuery = true)
    List<IdeaData> sortByIdea(Long eventId);


    @Query(value = "SELECT * FROM idea_table i where i.user_id = :userId", nativeQuery = true)
    List<IdeaEntity> findAllByUser(Long userId);

//    @Query(value = "SELECT ie.name_user as nameUser, hu.idea_id as ideaId, count(*) as counts, ie.id as id, ie.title as title, ie.email as email , ie.create_at as createAt FROM idea_table ie join human_emotions hu on hu.idea_id in (ie.id) where ie.event_id = :eventId group by hu.idea_id order by count(*) desc", nativeQuery = true)
//    List<IdeaData> findAllByLike(Long eventId);


    @Query(value = "SELECT date_format(c.create_at, '%M %d %Y') as createAt, count(c.id) AS ideaCount FROM web_top_up.idea_table c where  MONTH(create_at) =  MONTH(CURDATE()) GROUP BY MONTH(CURDATE())", nativeQuery = true)
    HumanEmotionData getIdeaInMoth();


}
