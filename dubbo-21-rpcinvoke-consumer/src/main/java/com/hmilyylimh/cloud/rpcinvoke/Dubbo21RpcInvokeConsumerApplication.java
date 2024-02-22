package com.hmilyylimh.cloud.rpcinvoke;

import com.hmilyylimh.cloud.rpcinvoke.invoke.GenericDemoService;
import com.hmilyylimh.cloud.rpcinvoke.invoke.InvokeDemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo21RpcInvokeConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo21RpcInvokeConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo21RpcInvokeConsumerApplication 】】】】】】已启动.");

        // 调用demo
        InvokeDemoService demoService = ctx.getBean(InvokeDemoService.class);
        System.out.println(demoService.invokeDemo());

        // 泛化调用demo
//        GenericDemoService genericDemoService = ctx.getBean(GenericDemoService.class);
//        System.out.println(genericDemoService.invokeDemo());
    }
}