package com.example.web_top_up.service.Impl;

import com.example.web_top_up.model.*;
import com.example.web_top_up.model.entities.CategoryEntity;
import com.example.web_top_up.model.entities.EventEntity;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.form.IdeaForm;
import com.example.web_top_up.model.entities.UserEntity;
import com.example.web_top_up.repositories.*;
import com.example.web_top_up.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class IdeaImpl implements IdeaService {


    @Autowired
    private IdeaRepository ideaRepository;

   @Autowired
   private EventReponsitory eventReponsitory;

   @Autowired
   private UserReponsitory userReponsitory;

    @Autowired
    private FileReponsitory fileReponsitory;

   @Autowired
   private CategoryReponsitory categoryReponsitory;


    @Override
    public List<IdeaEntity> findAllByEventId(Long id) {
        return ideaRepository.findAllByEventId(id);
    }

    @Override
    public List<IdeaEntity> findAllByCategory(Long id) {
        return ideaRepository.findAllByCategoryId(id);
    }

    @Override
    public IdeaEntity findIdeaById(Long id) {
        return ideaRepository.findById(id).orElseThrow();
    }

    @Override
    public IdeaForm findTestIdeaById(Long id) {
        return null;
    }


    @Override
    public List<IdeaEntity> findAll() {
        List<IdeaEntity> ideaEntityList = ideaRepository.findAll();
        return ideaEntityList;
    }

    @Override
    public IdeaEntity createIdea(IdeaForm ideaForm, Long id) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserEntity idUser = userReponsitory.findById(userReponsitory.findIdByEmail(userEmail)).orElseThrow();
        CategoryEntity category = categoryReponsitory.findById(ideaForm.getCategory()).orElseThrow();

        EventEntity event = eventReponsitory.findById(id).orElseThrow();


        IdeaEntity idea = new IdeaEntity();
        if (ideaForm.getIncognito() != null){
            idea.setNameUser("Anonymous user");
        }else {
            idea.setNameUser(idUser.getFirstName() + idUser.getLastName());
        }
        idea.setEvent(event);
        idea.setTitle(ideaForm.getTitle());
        idea.setEmail(userEmail);
        idea.setCategory(category);

        idea.setUser(idUser);
        idea.setCreateAt(LocalDateTime.now());

        return ideaRepository.save(idea);
    }

    @Override
    public IdeaEntity editIdea(IdeaForm ideaForm) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserEntity idUser = userReponsitory.findById(ideaForm.getUser()).orElseThrow();
        CategoryEntity category = categoryReponsitory.findById(ideaForm.getCategory()).orElseThrow();

        IdeaEntity idea = new IdeaEntity();
        if (ideaForm.getIncognito() != null){
            idea.setNameUser("Anonymous user");
        }else {
            idea.setNameUser(idUser.getFirstName() + idUser.getLastName());
        }
        idea.setId(ideaForm.getId());
        idea.setTitle(ideaForm.getTitle());
        idea.setEmail(userEmail);
        idea.setCategory(category);
        idea.setEvent(eventReponsitory.findById(ideaForm.getEvent()).orElseThrow());
        idea.setUser(idUser);
        idea.setCreateAt(LocalDateTime.now());

        return ideaRepository.save(idea);

    }

    @Override
    public List<IdeaReponse> sortByDepartment(Long id) {
        List<IdeaData> ideaData =  ideaRepository.sortByIdea(id);

        return ideaData.stream().map(
                ideaData1 -> new IdeaReponse(ideaData1.getId(),ideaData1.getTitle(),ideaData1.getEmail(),ideaData1.getCreateAt(),ideaData1.getIdeaId(),ideaData1.getCounts(),ideaData1.getNameUser(),
                        fileReponsitory.findByIdea(ideaData1.getId()).stream().map(fileData -> new FileResponse(fileData.getId(),fileData.getDocName())).collect(Collectors.toList()) )
        ).collect(Collectors.toList());
    }

    @Override
    public IdeaEntity updateIdea(IdeaForm ideaForm) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserEntity idUser = userReponsitory.findById(userReponsitory.findIdByEmail(userEmail)).orElseThrow();
        IdeaEntity idea = new IdeaEntity();
        CategoryEntity category;
        category = categoryReponsitory.findById(ideaForm.getCategory()).orElseThrow();
        idea.setTitle(ideaForm.getTitle());
        idea.setId(idea.getId());
        idea.setEmail(userEmail);
        idea.setCategory(category);
        idea.setEvent(eventReponsitory.findById(ideaForm.getEvent()).orElseThrow());
        idea.setUser(idUser);
        idea.setCreateAt(LocalDateTime.now());
        idea.setFileSubmissions(ideaForm.getFileSubmissions());

        return ideaRepository.save(idea);
    }

    @Override
    public void deleteIdea(Long id) {
        ideaRepository.deleteById(id);
    }

    @Override
    public List<IdeaEntity> findAllByUserId() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserEntity idUser = userReponsitory.findById(userReponsitory.findIdByEmail(userEmail)).orElseThrow();
        return ideaRepository.findAllByUser(idUser.getId());
    }

    @Override
    public List<IdeaEntity> findAllByFeel(Long id) {
        return null;
    }

    @Override
    public TotalLikeAndDislike getIdeaInMoth() {
        HumanEmotionData humanEmotionData = ideaRepository.getIdeaInMoth();

        if (humanEmotionData == null){
            return TotalLikeAndDislike.builder().countIdea(0).build();
        }else {
            return TotalLikeAndDislike.builder().countIdea(humanEmotionData.getIdeaCount()).build();
        }
    }


}
