package com.example.web_top_up.controller;


import com.example.web_top_up.domain.ResourceResponse;
import com.example.web_top_up.form.DepartmentForm;
import com.example.web_top_up.model.IdeaReponse;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.service.DepartmentService;
import com.example.web_top_up.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
//@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping(value= "/findIdeaInDepartment/{id}")
    public ResponseEntity<?> countDepartment(@PathVariable("id") Long id) {
        return new ResourceResponse<>(departmentService.findIdeaInDepartment(id));
    }

    @GetMapping(value = "/go_department")
    public String goCreate(){
        return "createDepartment";
    }

    @PostMapping (value = "/create_department")
    public String createSector(@ModelAttribute("departmentForm") DepartmentForm departmentForm){
        departmentService.createDepartment(departmentForm);
        return "redirect:/admin/showAllDepartment";
    }


    @GetMapping("/filter")
    public String filterIdeaInDepartment(@ModelAttribute("department") DepartmentForm departmentForm, Model model){
        List<IdeaReponse> ideaList =  departmentService.findIdeaInDepartment(departmentForm.getId());
        model.addAttribute("ideas", ideaList);
        return "filterIdeaInD";
    }

    @GetMapping(value = "/showAllDepartment")
    public String showDepartment(Model model){
        List<DepartmentEntity> entities = departmentService.findAllDepartment();
        model.addAttribute("departments", entities);
        return "showDepartment";
    }

    @GetMapping(value = "/deleteD")
    public String delete(@RequestParam("id") Long id){
        departmentService.deleteDepartment(id);
        return "redirect:/admin/showAllDepartment";
    }

    @GetMapping(value = "/edit_department")
    public String goEditDepartment(Model model,@RequestParam("id") Long id){
        DepartmentEntity department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "editDepartment";
    }

    @PostMapping(value = "/go_edit_department")
    private String editDepartment(@ModelAttribute("department") DepartmentForm departmentForm){
        departmentService.createDepartment(departmentForm);
        return "redirect:/admin/showAllDepartment";
    }






}
