package com.hmilyylimh.cloud.generic.bean;

import com.hmilyylimh.cloud.facade.user.bean.UserInfo;
import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dubbo15GenericBeanConsumerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Dubbo15GenericBeanConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo15GenericBeanConsumerApplication 】】】】】】已启动.");

        beanStyleRpcInvoke();
    }

    public static void beanStyleRpcInvoke()  {
        ReferenceConfig<GenericService> referenceConfig =
                buildReferenceConfig("com.hmilyylimh.cloud.facade.user.UserQueryFacade");
        referenceConfig.setGeneric("bean");

        // 模拟别人给过来的字节数组
        UserInfo reqUserInfo = buildUserInfo();

        // 远程调用
        GenericService genericService = referenceConfig.get();
        // 直接拿到引用的代理对象，然后进行远程调用
        Object rpcResult = genericService.$invoke("queryUserInfo",
                new String[]{"com.hmilyylimh.cloud.facade.user.bean.UserInfo"},
                new Object[]{JavaBeanSerializeUtil.serialize(reqUserInfo)});

        // 打印结果
        System.out.println(JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor)rpcResult));
    }

    private static UserInfo buildUserInfo()  {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("GeekDubbo15");
        userInfo.setName("泛化调用之Bean引用调用");
        userInfo.setSex("M");
        return userInfo;
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
