package com.hmilyylimh.cloud.codec;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo22CodecProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo22CodecProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo22CodecProviderApplication 】】】】】】已启动.");
    }
}