package com.example.web_top_up.service.Impl;

import com.example.web_top_up.form.CommentForm;
import com.example.web_top_up.form.EmailForm;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.repositories.IdeaRepository;
import com.example.web_top_up.service.EmailService;
import com.example.web_top_up.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class Emailmpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private IdeaRepository ideaRepository;

    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendMail(Long id, CommentForm commentForm) {
        try {
            IdeaEntity ideaEntity = ideaRepository.findById(id).orElseThrow();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(ideaEntity.getEmail());
            mailMessage.setText(commentForm.getContent() + "Commenter" + commentForm.getEmail());
            mailMessage.setSubject("New message");
            emailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
