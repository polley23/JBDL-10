package com.gfg.kafkademojbdl10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class resource {
    @Autowired
    ProducerService producerService;

    @PostMapping("/message")
    public void produce( @RequestBody MessageRequest messageRequest){
        producerService.produce(messageRequest.getTopic(),messageRequest.getMessage());
    }
}
