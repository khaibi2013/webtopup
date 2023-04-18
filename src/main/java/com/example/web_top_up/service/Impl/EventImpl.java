package com.example.web_top_up.service.Impl;

import com.example.web_top_up.model.EventData;
import com.example.web_top_up.model.EventReponse;
import com.example.web_top_up.model.entities.EventEntity;
import com.example.web_top_up.form.EventForm;
import com.example.web_top_up.repositories.EventReponsitory;
import com.example.web_top_up.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventImpl implements EventService {


    @Autowired
    private EventReponsitory eventReponsitory;

    @Override
    public List<EventEntity> findAllEvent() {
        return eventReponsitory.findAll();
    }

    @Override
    public EventEntity createEvent(EventForm eventForm) {
        Calendar calendar = Calendar.getInstance();
        EventEntity categoryEntity = new EventEntity();
        if(eventForm.getId() !=null){
            categoryEntity.setId(eventForm.getId());
            categoryEntity.setCreateAt(LocalDateTime.now());
            categoryEntity.setDueDate(eventForm.getDueDate());
            categoryEntity.setDueTime(eventForm.getDueTime());
            categoryEntity.setStartDate(eventForm.getStartDate());
            categoryEntity.setNameEvent(eventForm.getNameEvent());
            return eventReponsitory.save(categoryEntity);
        }else {
            categoryEntity.setCreateAt(LocalDateTime.now());
            categoryEntity.setStartDate(eventForm.getStartDate());
            categoryEntity.setDueDate(eventForm.getDueDate());
            categoryEntity.setDueTime(eventForm.getDueTime());
            categoryEntity.setNameEvent(eventForm.getNameEvent());

            return eventReponsitory.save(categoryEntity);

        }

    }

    @Override
    public EventEntity findById(Long id) {
        return eventReponsitory.findById(id).orElseThrow();
    }

    @Override
    public void deleteEvent(Long id) {
        eventReponsitory.deleteById(id);
    }

    @Override
    public List<EventReponse> findEventMoth() {
        List<EventData> dataList = eventReponsitory.getDataEvent();
        if (dataList ==null){
            return dataList.stream().map(data -> new EventReponse("",Long.valueOf(0), String.valueOf(LocalDate.now()))).collect(Collectors.toList());
        }else {
            return dataList.stream().map(data -> new EventReponse(data.getName(),data.getCounts(), data.getCreateAt())).collect(Collectors.toList());
        }


    }


}
