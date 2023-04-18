package com.example.web_top_up.service.Impl;

import com.example.web_top_up.model.entities.FileEntity;
import com.example.web_top_up.model.entities.IdeaEntity;
import com.example.web_top_up.repositories.FileReponsitory;
import com.example.web_top_up.repositories.IdeaRepository;
import com.example.web_top_up.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
public class FileIpml implements FileService {

    @Autowired
    private FileReponsitory fileReponsitory;

    @Autowired
    private IdeaRepository ideaRepository;


    @Override
    public FileEntity saveFile(MultipartFile multipartFile,Long id) {
        FileEntity fileEntity = new FileEntity();
        IdeaEntity idea = ideaRepository.findById(id).orElseThrow();
        String docName = multipartFile.getOriginalFilename();
        try{
            fileEntity.setDocName(docName);
            fileEntity.setDocType(multipartFile.getContentType());
            fileEntity.setData(multipartFile.getBytes());
            fileEntity.setIdea(idea);

            return fileReponsitory.save(fileEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<FileEntity> getFile(Long id) {
        return fileReponsitory.findById(id);
    }

    @Override
    public List<FileEntity> getAllFile(Long ideaId) {
        return fileReponsitory.findAllByIdeaId(ideaId);
    }

    @Override
    public void deleteFiles(Long id) {
        fileReponsitory.deleteById(id);
    }
}
