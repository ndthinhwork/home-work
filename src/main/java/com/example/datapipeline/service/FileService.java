package com.example.datapipeline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class FileService implements FileServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final KafkaService kafkaProducerService;

    public FileService(KafkaService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void processFile(MultipartFile file) throws IOException {
        logger.info("Processing file: {}", file.getOriginalFilename());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    if (line.startsWith("user_id,segment_type")) {
                        logger.debug("Skipping header line");
                        continue;
                    }
                }
                String[] data = line.split(",");
                if (data.length == 2) {
                    String userId = data[0].trim();
                    String segmentType = data[1].trim();
                    kafkaProducerService.sendMessage(userId, segmentType);
                }
            }
            logger.info("File processed successfully");
        }
    }
}
