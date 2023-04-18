package com.example.web_top_up.controller;

import com.example.web_top_up.model.entities.FileEntity;
import com.example.web_top_up.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/dowload/{fileId}")
    public ResponseEntity<ByteArrayResource> dowoad(@PathVariable Long fileId){
        FileEntity fileEntity = fileService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getDocType()))
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"attachment:filename=\""+fileEntity.getDocName()+"\"")
                .body(new ByteArrayResource(fileEntity.getData()));


    }


    @GetMapping("/deleteFile/{id}/idea/{idea}")
    public String delete(@PathVariable("id") Long id, @PathVariable("idea") Long idea){
        fileService.deleteFiles(id);
        return "redirect:/user/edit_idea/?id=" + idea;
    }

}
