package com.gfg.kafkademojbdl10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    KafkaTemplate<String,String>  kafkaTemplate;

    public void produce(String topic , String message){
        kafkaTemplate.send(topic,message);
    }

}
