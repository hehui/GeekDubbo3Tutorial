package com.hmilyylimh.cloud.arch;

import com.hmilyylimh.cloud.arch.util.TimeUtils;
import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:dubbo-12-arch-consumer.xml")
@SpringBootApplication
public class Dubbo12ArchConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo12ArchConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo12ArchConsumerApplication 】】】】】】已启动.");

        // 消费方启动成功后，就可以去将提供方关闭掉了，10 秒钟的缓冲期就是去关闭提供方的时间
        TimeUtils.sleep(10 * 1000);

        DemoFacade demoFacade = ctx.getBean(DemoFacade.class);
        System.out.println(demoFacade.sayHello("Geek"));
    }
}