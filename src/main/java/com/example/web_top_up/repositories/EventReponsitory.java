package com.example.web_top_up.repositories;

import com.example.web_top_up.model.EventData;
import com.example.web_top_up.model.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventReponsitory extends JpaRepository<EventEntity,Long> {


    @Query(value = "SELECT c.name_event as name, date_format(c.create_at, '%M %d %Y') as createAt, count(c.id) AS counts FROM event_table c GROUP BY MONTH(c.create_at), YEAR(c.create_at)", nativeQuery = true)
    List<EventData> getDataEvent();

}
