package com.atguigu.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private Sender sender;

    @PostMapping("/send/{msg}")
    public String send(@PathVariable("msg") String msg) {
        sender.send("httpTopic",msg);
        return msg;
    }
}

