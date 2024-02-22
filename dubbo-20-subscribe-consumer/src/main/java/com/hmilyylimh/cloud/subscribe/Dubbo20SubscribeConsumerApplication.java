package com.hmilyylimh.cloud.subscribe;

import com.hmilyylimh.cloud.subscribe.invoke.InvokeDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo20SubscribeConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo20SubscribeConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo20SubscribeConsumerApplication 】】】】】】已启动.");

        // 调用demo
        InvokeDemoService demoService = ctx.getBean(InvokeDemoService.class);
        System.out.println(demoService.invokeDemo());
    }
}