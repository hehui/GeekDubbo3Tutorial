package com.hmilyylimh.cloud.validation;

import com.hmilyylimh.cloud.validation.invoke.InvokeDemoFacade;
import com.hmilyylimh.cloud.validation.invoke.InvokeDemoFacade3;
import com.hmilyylimh.cloud.validation.invoke.InvokeDemoFacade4;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo07ValidationConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo07ValidationConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo07ValidationConsumerApplication 】】】】】】已启动.");

        // 调用
        InvokeDemoFacade invokeDemoFacade = ctx.getBean(InvokeDemoFacade.class);
//        System.out.println(invokeDemoFacade.invokeValidate("1", null, "F"));
        System.out.println(invokeDemoFacade.invokeValidate("1", "Geek", "F"));
    }

//    public static void main1(String[] args) {
//        // 从 Spring 的上下文中拿到 InvokeDemoFacade 的实例对象
//        InvokeDemoFacade invokeDemoFacade = SpringCtxUtils.getBean(InvokeDemoFacade.class);
//        // 然后想办法触发调用一下 invokeValidate 方法，目的是需要调用下游的 validateUser 方法
//        String retMsg = invokeDemoFacade.invokeValidate("1", "Geek", "F");
//        // 然后将返回的结果打印出来
//        System.out.println(retMsg);
//    }
}
