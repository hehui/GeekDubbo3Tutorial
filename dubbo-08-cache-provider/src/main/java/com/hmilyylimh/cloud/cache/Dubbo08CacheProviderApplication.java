package com.hmilyylimh.cloud.cache;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo08CacheProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo08CacheProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo08CacheProviderApplication 】】】】】】已启动.");
    }

}
