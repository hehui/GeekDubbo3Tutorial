package com.hmilyylimh.cloud.async;

import com.hmilyylimh.cloud.facade.async.AsyncOrderFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.remoting.exchange.ResponseCallback;
import org.apache.dubbo.remoting.exchange.ResponseFuture;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Dubbo05AsyncSetcallbackConsumerApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo05AsyncSetcallbackConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo05AsyncSetcallbackConsumerApplication 】】】】】】已启动.");

        setCallback(ctx);
    }

    private static void setCallback(ConfigurableApplicationContext ctx) throws Exception {
        // 创建引用 AsyncUserQueryFacade 这个服务的对象
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
        /**
         * <pre>
         *         <dependency>
         *             <groupId>org.apache.dubbo</groupId>
         *             <artifactId>dubbo</artifactId>
         *             <version>2.7.1</version>
         *         </dependency>
         * </pre>
         *
         * 这个 setCallback 接口，大家需要验证的话，可以将 dubbo 的版本切换为 2.7.1 即可验证
         */
        ResponseFuture future = ((FutureAdapter) RpcContext.getContext().getFuture()).getFuture();
        future.setCallback(new ResponseCallback() {
            // 成功的回调
            @Override
            public void done(Object response) {
                System.out.println("注定可以拿到结果1: " + response);
            }
            // 异常的回调
            @Override
            public void caught(Throwable exception) {
                System.out.println("非常意外走到了异常分支: " + exception.getLocalizedMessage());
            }
        });
        System.out.println("注定可以拿到结果2: " + future.get());
    }

}
