package com.example.datapipeline.controller;

import com.example.datapipeline.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/files")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processFile(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Received file: {}", file.getOriginalFilename());
        if (file.isEmpty()) {
            logger.warn("File is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        fileService.processFile(file);
        logger.info("File processing started");
        return ResponseEntity.ok("File is being processed");
    }
}
