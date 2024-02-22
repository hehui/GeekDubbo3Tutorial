package com.hmilyylimh.cloud.threadpool.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-12-30
 *
 */
@Configuration
public class ThreadPoolExtProviderConfig {

    /**
     * 声明应用名
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig("dubbo-26-threadpool-ext-provider");
    }

    /**
     * 声明注册中心配置
     */
    @Bean
    public RegistryConfig registryConfig() {
        return new RegistryConfig("zookeeper://127.0.0.1:2181");
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", 28260);
//        protocolConfig.setThreadpool("monitorfixed");
        return protocolConfig;
    }
}