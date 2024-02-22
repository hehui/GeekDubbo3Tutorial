package com.hmilyylimh.cloud.loadcfg;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Dubbo11LoadcfgProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo11LoadcfgProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo11LoadcfgProviderApplication 】】】】】】已启动.");
    }
}
