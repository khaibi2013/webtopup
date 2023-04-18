package com.example.web_top_up.controller;


import com.example.web_top_up.domain.ResourceResponse;
import com.example.web_top_up.service.HumanEmotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value = "/user")
public class HumanEmotionController {

    @Autowired
    private HumanEmotionsService humanEmotionsService;


//    @GetMapping("/feeling/{id}")
//    public ResponseEntity<?> findAllIdeaByDepartment(@PathVariable("id") Long id) {
//
//        return new ResourceResponse<>(humanEmotionsService.createLike(id));
//    }

    @GetMapping(value = "/likes")
    public String Like(@RequestParam("id") Long id){

        humanEmotionsService.createLike(id);
        return "redirect:/detail_Idea/?id=" + id;
    }

    @GetMapping(value = "/dislike")
    public String Dislike(@RequestParam("id") Long id){

        humanEmotionsService.createDislike(id);
        return "redirect:/detail_Idea/?id=" + id;
    }


}
