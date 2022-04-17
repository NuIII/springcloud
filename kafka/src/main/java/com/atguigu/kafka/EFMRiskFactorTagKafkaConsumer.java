
package com.atguigu.kafka;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class EFMRiskFactorTagKafkaConsumer {


    @KafkaListener(topics = "EFM_RISK_FACTOR_TAG111")
    public void listen(List<ConsumerRecord<String, String>> records){

    }
}
