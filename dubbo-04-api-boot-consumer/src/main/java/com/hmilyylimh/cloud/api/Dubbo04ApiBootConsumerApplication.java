package com.hmilyylimh.cloud.api;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:dubbo-04-api-boot-consumer.xml")
@SpringBootApplication
public class Dubbo04ApiBootConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo04ApiBootConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo04ApiBootConsumerApplication 】】】】】】已启动.");

        DemoFacade demoFacade = ctx.getBean(DemoFacade.class);
        System.out.println(demoFacade.sayHello("Geek"));
    }
}
