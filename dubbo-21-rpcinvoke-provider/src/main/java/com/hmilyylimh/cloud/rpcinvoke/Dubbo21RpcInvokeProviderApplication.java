package com.hmilyylimh.cloud.rpcinvoke;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo21RpcInvokeProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo21RpcInvokeProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo21RpcInvokeProviderApplication 】】】】】】已启动.");
    }
}