package com.example.web_top_up.controller;

import com.example.web_top_up.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

//    @PostMapping(value= "/create_reply/{id}")
//    public ResponseEntity<?> createComment(@PathVariable("id") Long id, @RequestBody ReplyForm replyForm) {
//
//
//        return new ResourceResponse<>(replyService.createReply(replyForm,id));
//    }
//
//    @DeleteMapping(value = "delete_comment/{id}")
//    public void deleteComment(@PathVariable("id") Long id) {
//        replyService.deleteReply(id);
//    }


}
