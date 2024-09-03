package com.example.datapipeline.service;

public interface KafkaServiceImpl {
    void sendMessage(String userId, String segmentType);
}
