package com.hmilyylimh.cloud.async.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-04
 */
@Configuration
public class AsyncProviderConfig {

    /**
     * 声明应用名
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig("dubbo-02-async-provider");
        return config;
    }

    /**
     * 声明注册中心配置
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig config = new RegistryConfig("zookeeper://127.0.0.1:2181");
        return config;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig config = new ProtocolConfig();
        config.setName("dubbo");
        config.setPort(28020);
        return config;
    }
}