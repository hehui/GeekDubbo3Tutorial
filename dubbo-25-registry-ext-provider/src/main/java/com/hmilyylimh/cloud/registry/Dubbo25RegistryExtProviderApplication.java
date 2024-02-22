package com.hmilyylimh.cloud.registry;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-12-26
 *
 */
@EnableDubbo
@SpringBootApplication
public class Dubbo25RegistryExtProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo25RegistryExtProviderApplication.class, args);
        System.out.println("【【【【【【 Dubbo25RegistryExtProviderApplication 】】】】】】已启动.");
    }
}