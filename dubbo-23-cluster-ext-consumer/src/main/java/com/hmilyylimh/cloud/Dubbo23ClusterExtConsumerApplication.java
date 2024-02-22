package com.hmilyylimh.cloud;

import com.hmilyylimh.cloud.invoke.InvokeDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo23ClusterExtConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo23ClusterExtConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo23ClusterExtConsumerApplication 】】】】】】已启动.");

        // 调用demo
        InvokeDemoService demoService = ctx.getBean(InvokeDemoService.class);
        System.out.println(demoService.invokeDemo());
    }
}