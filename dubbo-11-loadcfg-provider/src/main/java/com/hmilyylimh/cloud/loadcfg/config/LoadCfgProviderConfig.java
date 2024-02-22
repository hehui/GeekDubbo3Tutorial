package com.hmilyylimh.cloud.loadcfg.config;

import com.hmilyylimh.cloud.facade.crypto.CryptoFacade;
import org.apache.dubbo.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class LoadCfgProviderConfig {

    /**
     * 声明应用名
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig("dubbo-11-loadcfg-provider");
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
        return new ProtocolConfig("dubbo", 28110);
    }

    /**
     * 暴露UserServiceImpl的服务
     */
    @Bean
    public ServiceConfig<CryptoFacade> serviceConfigCryptoFacade(CryptoFacade cryptoFacade,
                                                                ApplicationConfig applicationConfig,
                                                                RegistryConfig registryConfig) {
        ServiceConfig<CryptoFacade> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(CryptoFacade.class);
        serviceConfig.setRef(cryptoFacade);

        // 注入applicationName
        serviceConfig.setApplication(applicationConfig);
        // 设置注册中心
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setTimeout(5000);

        List<MethodConfig> methods = new ArrayList<>();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("decrypt");
        methodConfig.setTimeout(3000);
        methods.add(methodConfig);
        serviceConfig.setMethods(methods);

        // 暴露的方法
        serviceConfig.export();
        return serviceConfig;
    }
}