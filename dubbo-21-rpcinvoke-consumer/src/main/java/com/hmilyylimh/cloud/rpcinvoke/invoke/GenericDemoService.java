package com.hmilyylimh.cloud.rpcinvoke.invoke;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-15
 */
@Component
public class GenericDemoService {

    public String invokeDemo() {
        return stringStyleRpcInvoke();
    }

    public static String stringStyleRpcInvoke() {
        ReferenceConfig<GenericService> referenceConfig =
                buildReferenceConfig("com.hmilyylimh.cloud.facade.demo.DemoFacade");
        referenceConfig.setGeneric("true");

        // 远程调用
        GenericService genericService = referenceConfig.get();
        Object rpcResult = genericService.$invoke(
                "sayHello",
                new String[]{"java.lang.String"},
                new Object[]{"Generic Demo"});

        // 返回结果
        return (String) rpcResult;
    }

    private static ReferenceConfig<GenericService> buildReferenceConfig(String interfaceName) {
        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();

        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboBootstrap.getApplicationModel().getApplicationName());

        // 设置注册中心的地址
        String address = dubboBootstrap.getConfigManager().getRegistries().iterator().next().getAddress();
        RegistryConfig registryConfig = new RegistryConfig(address);

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(interfaceName);
        return referenceConfig;
    }
}