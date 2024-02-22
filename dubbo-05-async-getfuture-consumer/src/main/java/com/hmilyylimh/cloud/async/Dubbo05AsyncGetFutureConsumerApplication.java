package com.hmilyylimh.cloud.async;

import com.hmilyylimh.cloud.facade.async.AsyncOrderFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class Dubbo05AsyncGetFutureConsumerApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo05AsyncGetFutureConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo05AsyncGetFutureConsumerApplication 】】】】】】已启动.");

        getFuture(ctx);
    }

    private static void getFuture(ConfigurableApplicationContext ctx) throws Exception {
        // 创建引用 AsyncOrderFacade 这个服务的对象
        ReferenceConfig<AsyncOrderFacade> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(ctx.getBean(ApplicationConfig.class));
        referenceConfig.setRegistry(ctx.getBean(RegistryConfig.class));
        referenceConfig.setInterface(AsyncOrderFacade.class);
        referenceConfig.setTimeout(12000);
        // 真正开启异步化操作，让调用方可以通过 Future 来拿到最终的结果
        referenceConfig.setAsync(true);

        // 直接拿到引用的代理对象，然后进行远程调用
        AsyncOrderFacade asyncOrderFacade = referenceConfig.get();

        // 既然是异步化操作获取结果，那么下面这行代码会有结果么？？？
        System.out.println("注定拿不到结果: " + asyncOrderFacade.queryOrderById("05"));

        // 拿到一个 Future 对象，这个 Future 对象哪里来呢？其实是从 RpcContext 远程调用上下文中获取得到的
        Object result = RpcContext.getContext().getFuture().get();
        System.out.println("注定可以拿到结果: " + result);
    }
}
