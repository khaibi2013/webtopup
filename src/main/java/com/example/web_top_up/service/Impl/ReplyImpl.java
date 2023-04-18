package com.example.web_top_up.service.Impl;

import com.example.web_top_up.model.entities.ReplyEntity;
import com.example.web_top_up.form.ReplyForm;
import com.example.web_top_up.repositories.ReplyReponsitory;
import com.example.web_top_up.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyImpl implements ReplyService {

    @Autowired
    private ReplyReponsitory replyReponsitory;


    @Override
    public ReplyEntity createReply(ReplyForm replyForm, Long id) {
        ReplyEntity entity = new ReplyEntity();
        entity.setCreateAt(LocalDateTime.now());
        entity.setContent(replyForm.getContent());
        entity.setEmail("name");


        return replyReponsitory.save(entity) ;
    }

    @Override
    public void deleteReply(Long id) {
        replyReponsitory.deleteById(id);
    }
}
