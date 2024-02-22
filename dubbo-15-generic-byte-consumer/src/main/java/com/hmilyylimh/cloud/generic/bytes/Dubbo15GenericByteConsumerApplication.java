package com.hmilyylimh.cloud.generic.bytes;

import com.hmilyylimh.cloud.facade.user.bean.UserInfo;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.io.UnsafeByteArrayInputStream;
import org.apache.dubbo.common.io.UnsafeByteArrayOutputStream;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Dubbo15GenericByteConsumerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Dubbo15GenericByteConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo15GenericByteConsumerApplication 】】】】】】已启动.");

        byteStyleRpcInvoke();
    }

    public static void byteStyleRpcInvoke() throws Exception {
        ReferenceConfig<GenericService> referenceConfig =
                buildReferenceConfig("com.hmilyylimh.cloud.facade.user.UserQueryFacade");
        referenceConfig.setGeneric("nativejava");

        // 模拟别人给过来的字节数组
        byte[] recvBytes = mockRecvBytes();

        // 远程调用
        GenericService genericService = referenceConfig.get();
        Object rpcResult = genericService.$invoke(
                "queryUserInfo",
                new String[]{"com.hmilyylimh.cloud.facade.user.bean.UserInfo"},
                new Object[]{recvBytes});

        // 打印结果
        System.out.println(parseRpcResult(rpcResult));
    }

    private static byte[] mockRecvBytes() throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("GeekDubbo15");
        userInfo.setName("泛化调用之byte数组调用");
        userInfo.setSex("M");

        // 直接拿到引用的代理对象，然后进行远程调用
        UnsafeByteArrayOutputStream out = new UnsafeByteArrayOutputStream();
        ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava")
                .serialize(null, out).writeObject(userInfo);
        return out.toByteArray();
    }

    private static Object parseRpcResult(Object rpcResult) throws IOException, ClassNotFoundException {
        UnsafeByteArrayInputStream in = new UnsafeByteArrayInputStream((byte[])rpcResult);
        return ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava")
                .deserialize(null, in).readObject();
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
