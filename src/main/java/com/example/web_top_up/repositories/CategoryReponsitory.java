package com.example.web_top_up.repositories;

import com.example.web_top_up.model.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryReponsitory extends JpaRepository<CategoryEntity,Long> {

    @Query(value = "SELECT * FROM category c ORDER BY c.id = :id  desc", nativeQuery = true)
    List<CategoryEntity> findAllById(CategoryEntity id);

}
