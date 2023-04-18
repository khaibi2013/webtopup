//package com.example.web_top_up.controller;
//
//
//import com.example.web_top_up.form.EmailForm;
//import com.example.web_top_up.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//public class EmailController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/sendMail")
//    public String sendMail(@RequestBody EmailForm emailForm) {
//        String status = emailService.sendMail();
//
//        return status;
//    }
//
//
//}
