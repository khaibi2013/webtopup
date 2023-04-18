package com.example.web_top_up.service.Impl;

import com.example.web_top_up.model.entities.CommentEntity;
import com.example.web_top_up.form.CommentForm;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.repositories.CommentReponsitory;
import com.example.web_top_up.repositories.IdeaRepository;
import com.example.web_top_up.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Autowired
    private IdeaRepository ideaRepository;

    @Override
    public CommentEntity createComment(CommentForm commentForm, Long id) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-dd HH:mm:ss");

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        IdeaEntity ideaEntity = ideaRepository.findById(id).orElseThrow();
        CommentEntity entity = new CommentEntity();
        entity.setCreateAt(LocalDateTime.now());
        entity.setContent(commentForm.getContent());
        entity.setIdea(ideaEntity);
        entity.setEmail(userEmail);

        return commentReponsitory.save(entity) ;
    }

    @Override
    public void deleteComment(Long id) {
        commentReponsitory.deleteById(id);
    }

    @Override
    public CommentEntity findCommentId(Long id) {
        return commentReponsitory.findById(id).orElseThrow();
    }

    @Override
    public CommentEntity editComment(CommentForm commentForm) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(commentForm.getId());
        commentEntity.setContent(commentForm.getContent());
        commentEntity.setEmail(commentEntity.getEmail());
        commentEntity.setIdea(ideaRepository.findById(commentForm.getIdea()).orElseThrow());
        commentEntity.setCreateAt(LocalDateTime.now());
        return commentReponsitory.save(commentEntity);

    }
}
