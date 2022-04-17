package com.atguigu.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @KafkaListener(topics = "httpTopic")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        System.out.println("【*** 消费者开始接收消息 ***】key = " + record.key() + "、value = " + record.value());
        //TODO，在这里进行自己的业务操作，例如入库
    }
}

