package com.example.web_top_up.service;

import com.example.web_top_up.form.CommentForm;
import com.example.web_top_up.form.EmailForm;

public interface EmailService {

    String sendMail(Long id, CommentForm commentForm);
}
