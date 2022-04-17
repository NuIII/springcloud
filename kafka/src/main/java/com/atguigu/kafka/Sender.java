package com.atguigu.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;


@Component
public class Sender {
    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);
    @Autowired
    private KafkaTemplate<String, String> template;

    public void send(String topic,String message) {
        template.send(topic, message).addCallback(success -> {
            if (Objects.isNull(success)) {
                return;
            }
            int partition = success.getRecordMetadata().partition();
            long offset = success.getRecordMetadata().offset();
            LOG.info("生产者【主题：" + topic + "，当前分片：" + partition + "，偏移量：" + offset + "】");
        }, failure -> {
            throw new RuntimeException("发送消息失败:" + failure.getMessage());
        });


    }
}
