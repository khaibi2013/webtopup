package com.example.web_top_up.controller;

import com.example.web_top_up.domain.ResourceResponse;
import com.example.web_top_up.model.EventReponse;
import com.example.web_top_up.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class DashBoot {

    @Autowired
    private EventService eventService;


    @GetMapping(value = "/dashboot")
    public String testdashboot(Model model){

        List<EventReponse> dataDash = eventService.findEventMoth();
        model.addAttribute("chartData", dataDash);
        return "dash";
    }

    private List<List<Object>> getChartData() {
        List<EventReponse> dataDash = eventService.findEventMoth();
        List<Object> full = null;
        for (EventReponse data: dataDash) {
             full = List.of(data.getName(), data.getCounts());
        }
        return List.of(full);
    }





//    @GetMapping(value= "/dashbootsss")
//    public ResponseEntity<?> countDepartment() {
//        return new ResourceResponse<>(eventService.findEventMoth());
//    }




}
