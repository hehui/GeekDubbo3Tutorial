package com.hmilyylimh.cloud.cache;

import com.hmilyylimh.cloud.cache.invoke.InvokeCacheFacade;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo08CacheConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo08CacheConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo08CacheConsumerApplication 】】】】】】已启动.");

        InvokeCacheFacade invokeCacheFacade = ctx.getBean(InvokeCacheFacade.class);
        invokeCacheFacade.invokeCache();
    }

}
