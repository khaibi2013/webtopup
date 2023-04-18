package com.example.web_top_up.service;

import com.example.web_top_up.model.entities.CommentEntity;
import com.example.web_top_up.form.CommentForm;

public interface CommentService {

    CommentEntity createComment(CommentForm commentForm, Long id);

    void deleteComment(Long id);

    CommentEntity findCommentId(Long id);

    CommentEntity editComment(CommentForm commentForm);
}
