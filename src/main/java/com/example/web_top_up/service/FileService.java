package com.example.web_top_up.service;

import com.example.web_top_up.model.entities.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {

    FileEntity saveFile(MultipartFile multipartFile, Long id);


    Optional<FileEntity> getFile(Long id);

    List<FileEntity> getAllFile(Long ideaId);

    void deleteFiles(Long id);



}
