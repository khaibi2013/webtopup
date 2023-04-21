package com.example.web_top_up.controller;


import com.example.web_top_up.form.DepartmentForm;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.model.entities.EventEntity;
import com.example.web_top_up.form.EventForm;
import com.example.web_top_up.service.DepartmentService;
import com.example.web_top_up.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.attribute.UserPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping(value = "/admin")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/showEvent")
    private String findAllCategories(Model model) {
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

        List<DepartmentEntity> listDepartment = departmentService.findAllDepartment();
        model.addAttribute("departments", listDepartment);
        model.addAttribute("department", new DepartmentForm());
        List<EventEntity> eventEntities = eventService.findAllEvent();
        model.addAttribute("eventss", eventEntities);

        return "showEvent";
    }


    @GetMapping("/QAM/go_event")
    public String goCreateCategory() {

        System.out.println(LocalDate.now());
        return "createEvent";
    }

    @PostMapping("/QAM/create_event")
    public String createEvent(@ModelAttribute("event") EventForm eventForm) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(eventForm.getStartDate());
        dateFormat.format(eventForm.getDueDate());
        eventService.createEvent(eventForm);
        return "redirect:/showEvent";
    }


    @GetMapping("QAM/deleteEvent")
    public String delete(@RequestParam("id") Long id) {
        eventService.deleteEvent(id);
        return "redirect:/showEvent";
    }

    @GetMapping("/admin/go_edit_Event")
    public String goCreateEvent(Model model, @RequestParam("id") Long id) {
        EventEntity event = eventService.findById(id);
        model.addAttribute("event", event);
        return "editEvent";
    }

    @PostMapping("/admin/update_event")
    public String editEvent(@ModelAttribute("event") EventForm eventForm) {
        eventService.createEvent(eventForm);
        return "redirect:/showEvent";
    }


}
