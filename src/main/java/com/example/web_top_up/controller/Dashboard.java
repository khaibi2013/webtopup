package com.example.web_top_up.controller;

import com.example.web_top_up.domain.ResourceResponse;
import com.example.web_top_up.model.EventReponse;
import com.example.web_top_up.model.TotalLikeAndDislike;
import com.example.web_top_up.service.EventService;
import com.example.web_top_up.service.HumanEmotionsService;
import com.example.web_top_up.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class Dashboard {


    @Autowired
    private HumanEmotionsService humanEmotionsService;

    @Autowired
    private EventService eventService;

    @Autowired
    private IdeaService ideaService;

    @GetMapping(value= "/dashbootsss")
    public ResponseEntity<?> countDepartment() {
        return new ResourceResponse<>(humanEmotionsService.getCountsLike());
    }

    @GetMapping(value= "/dashboard")
    public String countLike(Model model) {
        TotalLikeAndDislike likes = humanEmotionsService.getCountsLike();
        TotalLikeAndDislike dislike = humanEmotionsService.getCountDisLike();
        TotalLikeAndDislike idea = ideaService.getIdeaInMoth();
        List<EventReponse> dataDash = eventService.findEventMoth();
        model.addAttribute("chartData", dataDash);
        model.addAttribute("countLike", likes);
        model.addAttribute("dislike", dislike);
        model.addAttribute("idea", idea);
        return "dashboard";
    }


}
