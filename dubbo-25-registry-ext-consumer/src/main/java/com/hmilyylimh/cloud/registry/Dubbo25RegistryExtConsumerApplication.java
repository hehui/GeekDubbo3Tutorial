package com.hmilyylimh.cloud.registry;

import com.hmilyylimh.cloud.registry.invoke.InvokeDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo25RegistryExtConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo25RegistryExtConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo25RegistryExtConsumerApplication 】】】】】】已启动.");

        // 调用demo
        InvokeDemoService demoService = ctx.getBean(InvokeDemoService.class);
        System.out.println(demoService.invokeDemo());
    }
}