package com.example.web_top_up.service;

import com.example.web_top_up.model.IdeaReponse;
import com.example.web_top_up.model.TotalLikeAndDislike;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.form.IdeaForm;

import java.util.List;

public interface IdeaService {

    List<IdeaEntity> findAllByEventId(Long id);

    List<IdeaEntity> findAllByCategory(Long id);

    IdeaEntity findIdeaById(Long id);

    IdeaForm findTestIdeaById(Long id);


    List<IdeaEntity> findAll();

    IdeaEntity createIdea(IdeaForm profileForm, Long id);

    IdeaEntity editIdea(IdeaForm profileForm);




    List<IdeaReponse> sortByDepartment(Long id);

    IdeaEntity updateIdea(IdeaForm ideaForm);

    void deleteIdea(Long id);

    List<IdeaEntity> findAllByUserId();

    List<IdeaEntity> findAllByFeel(Long id);

    TotalLikeAndDislike getIdeaInMoth();

}
