package com.incident.platform.ingestionservice.consumer;
import com.incident.platform.ingestionservice.Repository.LogRepository;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author mednj
 **/
@Component
public class LogConsumer {

    private final LogRepository repository;

    public LogConsumer(LogRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "logs.raw")
    public void consume(GenericRecord record) {

        String level = record.get("level").toString();
        String message = record.get("message").toString();
        String service = record.get("service").toString();
        Long timestamp = (Long) record.get("timestamp");

        System.out.println("Received log: "+ level + " "+ message +" "+ service +"  "+ timestamp);
    }
}
