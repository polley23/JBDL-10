package com.gfg.kafkademojbdl10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "demo", groupId = "demo")
    public void consume(String message){
        log.info("Consumed message {}", message);
    }
}
