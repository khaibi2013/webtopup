package com.example.web_top_up.service;

import com.example.web_top_up.model.EventReponse;
import com.example.web_top_up.model.entities.EventEntity;
import com.example.web_top_up.form.EventForm;

import java.util.List;

public interface EventService {

    List<EventEntity> findAllEvent();

    EventEntity createEvent(EventForm categoryForm);

    EventEntity findById(Long id);

    void deleteEvent(Long id);

    List<EventReponse> findEventMoth();






}
