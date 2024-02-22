package com.hmilyylimh.cloud.subscribe;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo20SubscribeProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo20SubscribeProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo20SubscribeProviderApplication 】】】】】】已启动.");
    }
}