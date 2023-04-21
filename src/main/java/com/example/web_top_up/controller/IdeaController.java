package com.example.web_top_up.controller;

import com.example.web_top_up.domain.ResourceResponse;
import com.example.web_top_up.form.UserForm;
import com.example.web_top_up.model.HumanEmotionReponse;
import com.example.web_top_up.model.IdeaReponse;
import com.example.web_top_up.model.entities.CategoryEntity;
import com.example.web_top_up.model.entities.EventEntity;
import com.example.web_top_up.model.entities.FileEntity;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.form.IdeaForm;

import com.example.web_top_up.repositories.EventReponsitory;
import com.example.web_top_up.service.CategoryService;
import com.example.web_top_up.service.FileService;
import com.example.web_top_up.service.HumanEmotionsService;
import com.example.web_top_up.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//
//@RestController
@Controller
//@RequestMapping("/user")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @Autowired
    private EventReponsitory eventReponsitory;

    @Autowired
    private HumanEmotionsService humanEmotionsService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;



    @GetMapping(value = "/show_idea")
    public String showIdeaByEvent(Model model, @RequestParam("id") Long id){

        System.out.println("Test idea"+ id);
        List<IdeaEntity> ideas = ideaService.findAllByEventId(id);
        model.addAttribute("ideas",ideas );
        model.addAttribute("idE",id );
        return "showIdea";
    }

    @GetMapping(value = "/show_idea_category")
    public String showIdeaByCategory(Model model, @RequestParam("id") Long id){
        System.out.println("Test idea"+ id);
        List<IdeaEntity> ideas = ideaService.findAllByCategory(id);
        model.addAttribute("ideas",ideas );
        return "showIdea";
    }


    // All User Có quyền


    @GetMapping(value= "/user/go_createIdea")
    public String showFrom( Model model) {
        List<CategoryEntity> listCategory = categoryService.findAllCategory();

        model.addAttribute("categories", listCategory);
        model.addAttribute("ideaForm", new IdeaForm());

        return "createIdea";
    }


    @PostMapping(value = "/user/create_idea")
    public String createSector(@ModelAttribute("ideaForm") IdeaForm profileForm,
                               @RequestParam("id") Long id,
                               @RequestParam("files") MultipartFile[] multipartFiles) {



        IdeaEntity ideaEntity = ideaService.createIdea(profileForm,id);

        for (MultipartFile file : multipartFiles){
            fileService.saveFile(file,ideaEntity.getId());
        }

        return "redirect:/show_idea/?id=" + id;
    }

    @GetMapping(value = "/detail_Idea")
    public String detailsIdea(Model model, @RequestParam("id") Long id){
        IdeaEntity idea = ideaService.findIdeaById(id);
        HumanEmotionReponse humanEmotionReponse = humanEmotionsService.findLikes(id);
        HumanEmotionReponse dislike = humanEmotionsService.findCountDislike(id);
        model.addAttribute("idea", idea);
        model.addAttribute("felling", humanEmotionReponse);
        model.addAttribute("fellingDislike", dislike);
        return "detailIdea";
    }


    @GetMapping(value ="/user/edit_idea" )
    public String goEditIdea( @RequestParam("id") Long id, Model model){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        IdeaEntity ideaId = ideaService.findIdeaById(id);

        if (userEmail.equals(ideaId.getEmail())){

            List<CategoryEntity> categoryEntityList = categoryService.findAllCategoryById(ideaId.getCategory());
            model.addAttribute("ideass", ideaId);
            model.addAttribute("categories", categoryEntityList);
            return "editIdea";
        }

//        return "redirect:/show_idea/?id=" + ideaId.getEvent().getId();
        return "redirect:/user/my_idea";
    }

    @PostMapping(value = "/user/go_edit_idea")
    public String saveIdea(@ModelAttribute("ideaForm") IdeaForm ideaForm, @RequestParam("files") MultipartFile[] multipartFiles){
        EventEntity event = eventReponsitory.findById(ideaForm.getEvent()).orElseThrow();
        ideaService.editIdea(ideaForm);

        for (MultipartFile file : multipartFiles){
            if (!file.isEmpty()){
                fileService.saveFile(file, ideaForm.getId());
            }

        }
        return "redirect:/user/my_idea";
    }


    @GetMapping(value ="/user/delete_idea")
    public String delete(@RequestParam("id") Long id){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        IdeaEntity ids = ideaService.findIdeaById(id);
        System.out.println(ids.getEmail());

        if (userEmail.equals(ids.getEmail())) {
            ideaService.deleteIdea(id);
//            return "redirect:/show_idea/?id=" + idea;
            return "redirect:/user/my_idea";
        }

//        return "redirect:/show_idea/?id=" + idea;
        return "redirect:/user/my_idea";
    }


    @GetMapping(value ="/user/my_idea")
    public String findAllByUser(Model model){
        List<IdeaEntity> ideas = ideaService.findAllByUserId();
        model.addAttribute("ideas", ideas);
        return "myIdea";
    }


    @GetMapping(value= "/user/findAllIdea/{id}")
    public ResponseEntity<?> findAllI(@PathVariable("id") Long id) {
        return new ResourceResponse<>(ideaService.sortByDepartment(id));
    }


    @GetMapping(value = "/filterByLikeAndDislike")
    public String filterByLikeAndDislike(Model model, @RequestParam("id") Long id){

        List<IdeaReponse> ideas = ideaService.sortByDepartment(id);

        model.addAttribute("idea",ideas );

        return "filterFeeling";
    }















}
