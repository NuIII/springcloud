package com.atguigu.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KafkaMainApplication {

    //初始化系统属性
//    static {
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        System.setProperty("java.security.auth.login.config",
//                loader.getResource("").getPath()+ File.separator+"kafka_client_jaas.conf");
//    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaMainApplication.class, args);
    }
}
