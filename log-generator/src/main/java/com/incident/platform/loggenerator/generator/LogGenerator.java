package com.incident.platform.loggenerator.generator;

/**
 * @author mednj
 **/

import com.incident.platform.loggenerator.model.LogEvent;
import com.incident.platform.loggenerator.producer.LogProducer;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

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

        LogEvent event = new LogEvent(
                services[random.nextInt(services.length)],
                levels[random.nextInt(levels.length)],
                "Generated log event",
                System.currentTimeMillis()
        );

        producer.send(event);


        // to Refactor use Log4j instead
        System.out.println("Generated: " + event.getService() +
                " - " + event.getLevel());

    }
}