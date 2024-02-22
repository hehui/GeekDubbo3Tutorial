package com.hmilyylimh.cloud.cluster;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo23ClusterExtProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo23ClusterExtProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo23ClusterExtProviderApplication 】】】】】】已启动.");
    }
}