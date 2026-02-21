package com.incident.platform.loggenerator.generator;

/**
 * @author mednj
 **/
import com.incident.platform.loggenerator.producer.LogProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Random;
import com.incident.platform.avro.LogEvent;
@Component
public class LogGenerator {

    private final LogProducer producer;
    private final Random random = new Random();

    public LogGenerator(LogProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRate = 500)
    public void generate() {

        String[] services = {"order", "payment", "inventory"};
        String[] levels = {"INFO", "WARN", "ERROR"};

        LogEvent event = LogEvent.newBuilder()
                .setService(services[random.nextInt(services.length)])
                .setLevel(levels[random.nextInt(levels.length)])
                .setMessage("Sample log message")
                .setTimestamp(System.currentTimeMillis())
                .build();

        producer.send(event);


        // to Refactor use Log4j instead
        System.out.println("Generated: " + event.getService() +
                " - " + event.getLevel());

    }
}