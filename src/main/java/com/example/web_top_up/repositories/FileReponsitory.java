package com.example.web_top_up.repositories;

import com.example.web_top_up.model.FileData;
import com.example.web_top_up.model.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileReponsitory extends JpaRepository<FileEntity,Long> {



    @Query(value = "SELECT * FROM docs d where d.idea_id = :ideaId",nativeQuery = true)
    List<FileEntity> findAllByIdeaId(Long ideaId);

    @Query(value = "select f.id as id, f.doc_name as docName from web_top_up.files f where f.idea_id = :ideaId ",nativeQuery = true)
    List<FileData> findByIdea(Long ideaId);



}
