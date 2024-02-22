package com.hmilyylimh.cloud.codec;

import com.hmilyylimh.cloud.codec.invoke.InvokeDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo22CodecConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo22CodecConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo22CodecConsumerApplication 】】】】】】已启动.");

        // 调用demo
        InvokeDemoService demoService = ctx.getBean(InvokeDemoService.class);
        System.out.println(demoService.invokeDemo());
    }
}