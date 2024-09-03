package com.example.datapipeline.service;


import com.example.datapipeline.common.Constraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements KafkaServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Constraint constraint;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, Constraint constraint) {
        this.kafkaTemplate = kafkaTemplate;
        this.constraint = constraint;
    }

    @Override
    public void sendMessage(String userId, String segmentType) {
        String message = userId + "," + segmentType;
        String topic = constraint.topic;
        logger.info("Sending message to Kafka topic {}: {}", topic, message);
        kafkaTemplate.send(topic, message);
    }
}
