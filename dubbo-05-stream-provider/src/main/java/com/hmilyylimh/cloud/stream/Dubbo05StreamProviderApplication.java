package com.hmilyylimh.cloud.stream;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo05StreamProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo05StreamProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo05StreamProviderApplication 】】】】】】已启动.");
    }
}
