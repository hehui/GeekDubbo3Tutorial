package com.hmilyylimh.cloud.anno;

import com.hmilyylimh.cloud.anno.demo.InvokeDemoFacade;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo04AnnotationBootConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo04AnnotationBootConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo04AnnotationBootConsumerApplication 】】】】】】已启动.");

        InvokeDemoFacade invokeDemoFacade = ctx.getBean(InvokeDemoFacade.class);
        System.out.println(invokeDemoFacade.hello("Geek"));
    }
}
