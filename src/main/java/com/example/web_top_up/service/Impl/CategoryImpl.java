package com.example.web_top_up.service.Impl;

import com.example.web_top_up.form.CategoryForm;
import com.example.web_top_up.model.entities.CategoryEntity;
import com.example.web_top_up.repositories.CategoryReponsitory;
import com.example.web_top_up.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryReponsitory categoryReponsitory;

    @Override
    public List<CategoryEntity> findAllCategory() {
        return categoryReponsitory.findAll();
    }

    @Override
    public CategoryEntity createCategory(CategoryForm categoryForm) {
        CategoryEntity categoryE = new CategoryEntity();
        if(categoryForm.getId() != null){
            categoryE.setId(categoryForm.getId());
            categoryE.setNameCategory(categoryForm.getNameCategory());
            return categoryReponsitory.save(categoryE);
        }else {
            categoryE.setNameCategory(categoryForm.getNameCategory());
            return categoryReponsitory.save(categoryE);
        }

    }

    @Override
    public void deleteCategory(Long id) {
        categoryReponsitory.deleteById(id);
    }

    @Override
    public CategoryEntity findById(Long id) {
        return categoryReponsitory.findById(id).orElseThrow();
    }

    @Override
    public List<CategoryEntity> findAllCategoryById(CategoryEntity id) {
        return categoryReponsitory.findAllById(id);
    }
}
