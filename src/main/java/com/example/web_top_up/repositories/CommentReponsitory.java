package com.example.web_top_up.repositories;

import com.example.web_top_up.model.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReponsitory extends JpaRepository<CommentEntity,Long> {


}
