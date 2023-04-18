package com.example.web_top_up.service;

import com.example.web_top_up.form.CategoryForm;
import com.example.web_top_up.model.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> findAllCategory();

    CategoryEntity createCategory(CategoryForm categoryForm);


    void deleteCategory(Long id);

    CategoryEntity findById(Long id);


    List<CategoryEntity> findAllCategoryById(CategoryEntity id);


//    List<CategoryEntity> findAllCategoryById(CategoryEntity category);
}
