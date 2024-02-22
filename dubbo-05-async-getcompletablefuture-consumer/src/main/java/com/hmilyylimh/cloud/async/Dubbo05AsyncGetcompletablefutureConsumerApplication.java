package com.hmilyylimh.cloud.async;

import com.hmilyylimh.cloud.facade.async.AsyncOrderFacade;
import com.hmilyylimh.cloud.facade.async.bean.OrderInfo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class Dubbo05AsyncGetcompletablefutureConsumerApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo05AsyncGetcompletablefutureConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo05AsyncGetFutureConsumerApplication 】】】】】】已启动.");

        getCompletablefuture1(ctx);
        System.out.println("-----------------------------");
        getCompletablefuture2(ctx);
    }

    private static void getCompletablefuture1(ConfigurableApplicationContext ctx) throws Exception {
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
        System.out.println("注定拿不到结果: " + asyncOrderFacade.queryOrderById("05_1"));

        CompletableFuture<Object> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((result, e) -> {
            if(e == null){
                // 拿到一个 Future 对象，这个 Future 对象哪里来呢？其实是从 RpcContext 远程调用上下文中获取得到的
                System.out.println("注定可以拿到结果1: " + result);
            } else {
                System.out.println("非常意外走到了异常分支: " + e.getLocalizedMessage());
            }
        });
        System.out.println("注定可以拿到结果2: " + future.get());
    }

    private static void getCompletablefuture2(ConfigurableApplicationContext ctx) throws Exception {
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
        CompletableFuture<OrderInfo> future = asyncOrderFacade.queryDetailById("05_2");
        future.whenComplete((result, e) -> {
            if(e == null){
                // 拿到一个 Future 对象，这个 Future 对象哪里来呢？其实是从 RpcContext 远程调用上下文中获取得到的
                System.out.println("注定可以拿到结果1: " + result);
            } else {
                System.out.println("非常意外走到了异常分支: " + e.getLocalizedMessage());
            }
        });
        System.out.println("注定可以拿到结果2: " + future.get());
    }
}
