package com.hmilyylimh.cloud.deploy;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo19DubboDeployProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo19DubboDeployProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo19DubboDeployProviderApplication 】】】】】】已启动.");
    }
}