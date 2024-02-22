package com.hmilyylimh.cloud.dubbo;

import com.hmilyylimh.cloud.dubbo.spi.IDemoSpi;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.model.ApplicationModel;

public class Dubbo14DubboSpiApplication {

    public static void main(String[] args) {
        ApplicationModel applicationModel = ApplicationModel.defaultModel();
        // 通过 Protocol 获取指定像 ServiceLoader 一样的加载器
        ExtensionLoader<IDemoSpi> extensionLoader = applicationModel.getExtensionLoader(IDemoSpi.class);

        // 通过指定的名称从加载器中获取指定的实现类
        IDemoSpi customSpi = extensionLoader.getExtension("customSpi");
        System.out.println(customSpi + ", " + customSpi.getDefaultPort());

        // 再次通过指定的名称从加载器中获取指定的实现类，看看打印的引用是否创建了新对象
        IDemoSpi customSpi2 = extensionLoader.getExtension("customSpi");
        System.out.println(customSpi2 + ", " + customSpi2.getDefaultPort());
    }
}