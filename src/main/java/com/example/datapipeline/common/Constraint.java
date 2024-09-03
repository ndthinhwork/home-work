package com.example.datapipeline.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constraint {
    @Value("${spring.kafka.template.default-topic}")
    public String topic;
}
