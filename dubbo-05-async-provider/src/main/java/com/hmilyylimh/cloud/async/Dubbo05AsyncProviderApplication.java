package com.hmilyylimh.cloud.async;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo05AsyncProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo05AsyncProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo05AsyncProviderApplication 】】】】】】已启动.");
    }
}