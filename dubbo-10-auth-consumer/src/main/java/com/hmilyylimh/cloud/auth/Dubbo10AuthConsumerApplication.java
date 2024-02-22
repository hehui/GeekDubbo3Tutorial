package com.hmilyylimh.cloud.auth;

import com.hmilyylimh.cloud.auth.invoke.InvokeAuthFacade;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo10AuthConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo10AuthConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo10AuthConsumerApplication 】】】】】】已启动.");

        InvokeAuthFacade invokeLimitFacade = ctx.getBean(InvokeAuthFacade.class);
        invokeLimitFacade.invokeAuth();
    }

}
