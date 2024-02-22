package com.hmilyylimh.cloud.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo10AuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo10AuthProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo10AuthProviderApplication 】】】】】】已启动.");
    }

}
