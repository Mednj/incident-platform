package com.incident.platform.loggenerator.producer;

/**
 * @author mednj
 **/

import com.incident.platform.avro.LogEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducer {

    private final KafkaTemplate<String, LogEvent> kafkaTemplate;

    public LogProducer(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LogEvent event) {
        kafkaTemplate.send("logs.raw", event);
    }
}
