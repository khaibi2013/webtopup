package com.example.web_top_up.service;

import com.example.web_top_up.model.entities.ReplyEntity;
import com.example.web_top_up.form.ReplyForm;

public interface ReplyService {

    ReplyEntity createReply(ReplyForm replyForm, Long id);

    void deleteReply(Long id);
}
