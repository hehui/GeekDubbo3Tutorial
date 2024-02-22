package com.hmilyylimh.cloud.generic.json;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Dubbo15GenericJsonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo15GenericJsonConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo15GenericJsonConsumerApplication 】】】】】】已启动.");

        jsonStyleRpcInvoke();
    }

    public static void jsonStyleRpcInvoke() {
        ReferenceConfig<GenericService> referenceConfig =
                buildReferenceConfig("com.hmilyylimh.cloud.facade.user.UserQueryFacade");
        referenceConfig.setGeneric("true");

        // 模拟别人给过来的字节数组
        Map reqMap = mockJsonToMap();

        // 远程调用
        GenericService genericService = referenceConfig.get();
        Object rpcResult = genericService.$invoke(
                "queryUserInfo",
                new String[]{"com.hmilyylimh.cloud.facade.user.bean.UserInfo"},
                new Object[]{reqMap});

        // 打印结果
        System.out.println(rpcResult);
    }

    private static Map mockJsonToMap() {
        String recvJsonStr = "{\"id\": \"GeekDubbo15\", \"name\": \"泛化调用之Json形式调用\", \"sex\": \"M\"}";

        return JSON.parseObject(recvJsonStr, HashMap.class);
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
