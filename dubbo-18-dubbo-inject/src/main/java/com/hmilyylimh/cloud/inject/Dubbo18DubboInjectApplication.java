package com.hmilyylimh.cloud.inject;

import com.hmilyylimh.cloud.inject.spi.Geek;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.model.ApplicationModel;

//@SpringBootApplication
public class Dubbo18DubboInjectApplication {

    public static void main(String[] args) {
        ApplicationModel applicationModel = ApplicationModel.defaultModel();
        // 通过 Geek 接口获取指定像 扩展点加载器
        ExtensionLoader<Geek> extensionLoader = applicationModel.getExtensionLoader(Geek.class);

        Geek geek = extensionLoader.getAdaptiveExtension();
        System.out.println("日志1：【指定的 geek=springcloud 的情况】动态获取结果为: "
                + geek.getCourse(URL.valueOf("xyz://127.0.0.1/?geek=springcloud")));
        System.out.println("日志2：【指定的 geek=dubbo 的情况】动态获取结果为: "
                + geek.getCourse(URL.valueOf("xyz://127.0.0.1/?geek=dubbo")));
        System.out.println("日志3：【不指定的 geek 走默认情况】动态获取结果为: "
                + geek.getCourse(URL.valueOf("xyz://127.0.0.1/")));
    }
}
