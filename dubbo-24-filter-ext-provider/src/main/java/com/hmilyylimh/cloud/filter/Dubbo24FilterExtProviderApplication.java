package com.hmilyylimh.cloud.filter;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo24FilterExtProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo24FilterExtProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo24FilterExtProviderApplication 】】】】】】已启动.");
    }
}