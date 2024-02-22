package com.hmilyylimh.cloud.event;

import com.hmilyylimh.cloud.event.demo.InvokeDemoFacade;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo06EventConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo06EventConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo06EventConsumerApplication 】】】】】】已启动.");

        InvokeDemoFacade invokeDemoFacade = ctx.getBean(InvokeDemoFacade.class);
        System.out.println(invokeDemoFacade.hello("Geek"));
    }
}
