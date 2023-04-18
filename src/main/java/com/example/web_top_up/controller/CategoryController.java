package com.example.web_top_up.controller;


import com.example.web_top_up.form.CategoryForm;
import com.example.web_top_up.model.entities.CategoryEntity;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.service.CategoryService;
import com.example.web_top_up.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/QAM")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping(value = "/go_category")
    private String goCategory(){
        return "createCategory";
    }

    @PostMapping(value = "/create_category")
    private String createCategory(@ModelAttribute("category") CategoryForm categoryForm){
        categoryService.createCategory(categoryForm);
        return "redirect:/QAM/showCategory";
    }

    @GetMapping(value = "/showCategory")
    private String showCategory(Model model){

        List<CategoryEntity> listCategory = categoryService.findAllCategory();
        model.addAttribute("categories", listCategory);
        return "showCategory";
    }

    @GetMapping(value = "/delete")
    private String delete(@RequestParam("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/QAM/showCategory";
    }

    @GetMapping(value = "/editCategory")
    private String goEditCategory(Model model, @RequestParam("id") Long id){
        CategoryEntity category = categoryService.findById(id);
        model.addAttribute("category", category);

        return "editCategory";
    }

    @PostMapping(value = "/go_edit")
    private String editCategory(@ModelAttribute("category") CategoryForm categoryForm){
        categoryService.createCategory(categoryForm);
        return "redirect:/QAM/showCategory";
    }









}
