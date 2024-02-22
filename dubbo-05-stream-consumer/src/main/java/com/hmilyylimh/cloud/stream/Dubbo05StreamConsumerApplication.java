package com.hmilyylimh.cloud.stream;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo05StreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo05StreamConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo05StreamConsumerApplication 】】】】】】已启动.");
    }
}
