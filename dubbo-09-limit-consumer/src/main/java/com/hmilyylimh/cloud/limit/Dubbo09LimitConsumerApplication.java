package com.hmilyylimh.cloud.limit;

import com.hmilyylimh.cloud.limit.invoke.InvokeLimitFacade;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo09LimitConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo09LimitConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo09LimitConsumerApplication 】】】】】】已启动.");

        InvokeLimitFacade invokeLimitFacade = ctx.getBean(InvokeLimitFacade.class);
        invokeLimitFacade.invokeFilter();
    }
}
