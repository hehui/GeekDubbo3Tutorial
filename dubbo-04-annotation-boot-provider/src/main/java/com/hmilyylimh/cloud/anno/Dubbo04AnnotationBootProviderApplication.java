package com.hmilyylimh.cloud.anno;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo04AnnotationBootProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo04AnnotationBootProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo04AnnotationBootProviderApplication 】】】】】】已启动.");
    }
}
