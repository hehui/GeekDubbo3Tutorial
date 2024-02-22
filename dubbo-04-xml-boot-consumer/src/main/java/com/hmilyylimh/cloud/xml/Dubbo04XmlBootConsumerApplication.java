package com.hmilyylimh.cloud.xml;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

// 导入启动消费方所需要的XML配置文件
@ImportResource("classpath:dubbo-04-xml-boot-consumer.xml")
// SpringBoot应用的一键式启动注解
@SpringBootApplication
public class Dubbo04XmlBootConsumerApplication {

    public static void main(String[] args) {
        // 调用最为普通常见的应用启动API
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo04XmlBootConsumerApplication.class, args);
        // 启动成功后打印一条日志
        System.out.println("【【【【【【 Dubbo04XmlBootConsumerApplication 】】】】】】已启动.");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 然后向提供方暴露的 DemoFacade 接口服务进行远程RPC调用
        DemoFacade demoFacade = ctx.getBean(DemoFacade.class);
        // 将远程调用返回的结果进行打印输出
        System.out.println(demoFacade.sayHello("Geek"));
    }
}
