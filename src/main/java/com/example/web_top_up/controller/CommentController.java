package com.example.web_top_up.controller;

import com.example.web_top_up.form.CommentForm;
import com.example.web_top_up.model.entities.CommentEntity;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.repositories.IdeaRepository;
import com.example.web_top_up.service.CommentService;
import com.example.web_top_up.service.EmailService;
import com.example.web_top_up.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private EmailService emailService;



    @PostMapping(value = "/comment/{id}")
    public String createComment(@PathVariable("id") Long id, @ModelAttribute("commentA") CommentForm commentForm) {
        System.out.println("Code vào khong ");

        commentService.createComment(commentForm,id);
        emailService.sendMail(id, commentForm);

        return "redirect:/detail_Idea/?id=" + id;
    }

    @GetMapping(value = "delete_comment")
    public String deleteComment(@RequestParam("id") Long id, @Param("ids") Long ids) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        CommentEntity commentEntity = commentService.findCommentId(id);
        if (userEmail.equals(commentEntity.getEmail())){
            commentService.deleteComment(id);
            return "redirect:/detail_Idea/?id=" + ids;
        }
        return "redirect:/detail_Idea/?id=" + ids;
    }

    @PostMapping(value = "go_edit")
    public String goPageEditComment( @ModelAttribute("comment") CommentForm commentForm, @RequestParam("ids") Long id) {
        commentService.editComment(commentForm);
        System.out.println("Tess" + id);
        return "redirect:/detail_Idea/?id=" + id;
    }

    @GetMapping(value = "edit_comment")
    public String goPageEditComment(@RequestParam("id") Long id,@Param("ids") Long ids, Model model) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        CommentEntity commentEntity = commentService.findCommentId(id);
        System.out.println("kkk" + ids);
        if (userEmail.equals(commentEntity.getEmail())){
            model.addAttribute("comment", commentEntity);
            return "editComment";
        }

        return "redirect:/detail_Idea/?id=" + ids;
    }

}
