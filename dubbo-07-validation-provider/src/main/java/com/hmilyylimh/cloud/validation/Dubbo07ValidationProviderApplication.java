package com.hmilyylimh.cloud.validation;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo07ValidationProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo07ValidationProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo07ValidationProviderApplication 】】】】】】已启动.");
    }
}
