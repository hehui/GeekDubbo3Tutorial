package com.hmilyylimh.cloud.generic;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo15GenericProviderApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.security.serialize.generic.native-java-enable", "true");
        SpringApplication.run(Dubbo15GenericProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo15GenericProviderApplication 】】】】】】已启动.");
    }

}
