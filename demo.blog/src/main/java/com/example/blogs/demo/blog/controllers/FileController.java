package com.example.blogs.demo.blog.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController

public class FileController {

    @Autowired
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    @PostMapping("/uploadFiles")
    public ResponseEntity<?> uploadMultipleFiles(

            @RequestParam("images")MultipartFile[] files
            ){

        this.logger.info("{} No of files uploaded",files.length);

        Arrays.stream(files).forEach(file ->{
            logger.info("file name : {}",file.getOriginalFilename());
            logger.info("fileType",file.getContentType());
        });
        return ResponseEntity.ok("File Uploaded");

    }
}
