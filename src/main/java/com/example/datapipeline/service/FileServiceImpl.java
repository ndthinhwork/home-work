package com.example.datapipeline.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileServiceImpl {
    void processFile(MultipartFile file) throws IOException;
}
