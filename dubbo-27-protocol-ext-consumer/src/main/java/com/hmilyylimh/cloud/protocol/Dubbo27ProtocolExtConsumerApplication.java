package com.hmilyylimh.cloud.protocol;

import com.hmilyylimh.cloud.protocol.invoke.InvokeDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo27ProtocolExtConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo27ProtocolExtConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo27ProtocolExtConsumerApplication 】】】】】】已启动.");

        // 调用demo
        InvokeDemoService demoService = ctx.getBean(InvokeDemoService.class);
        System.out.println(demoService.invokeDemo());
    }
}