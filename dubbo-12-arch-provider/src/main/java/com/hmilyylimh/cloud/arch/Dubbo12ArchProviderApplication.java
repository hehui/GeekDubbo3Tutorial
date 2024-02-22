package com.hmilyylimh.cloud.arch;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:dubbo-12-arch-provider.xml")
@SpringBootApplication
public class Dubbo12ArchProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo12ArchProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo12ArchProviderApplication 】】】】】】已启动.");
    }

}
