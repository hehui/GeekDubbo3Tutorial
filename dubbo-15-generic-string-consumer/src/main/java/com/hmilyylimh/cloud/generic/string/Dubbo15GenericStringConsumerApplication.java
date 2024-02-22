package com.hmilyylimh.cloud.generic.string;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dubbo15GenericStringConsumerApplication {

    public static void main(String[] args)  {
        SpringApplication.run(Dubbo15GenericStringConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo15GenericStringConsumerApplication 】】】】】】已启动.");

        stringStyleRpcInvoke();
    }

    public static void stringStyleRpcInvoke() {
        ReferenceConfig<GenericService> referenceConfig =
                buildReferenceConfig("com.hmilyylimh.cloud.facade.user.UserQueryFacade");
        referenceConfig.setGeneric("true");

        // 远程调用
        GenericService genericService = referenceConfig.get();
        Object rpcResult = genericService.$invoke(
                "queryUser",
                new String[]{"java.lang.String"},
                new Object[]{"GeekDubbo15--泛化调用之String字符调用"});

        // 打印结果
        System.out.println(rpcResult);
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