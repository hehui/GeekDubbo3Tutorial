package com.hmilyylimh.cloud.async.order;

import com.hmilyylimh.cloud.facade.async.AsyncOrderFacade;
import com.hmilyylimh.cloud.facade.async.bean.OrderInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-25
 */
@DubboService
@Component
public class AsyncOrderFacadeImpl implements AsyncOrderFacade {

    @Override
    public OrderInfo queryOrderById(String id) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        AsyncContext asyncContext = RpcContext.startAsync();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                asyncContext.signalContextSwitch();

                // 这里模拟执行一段耗时的业务逻辑
                sleepInner(1000);

                OrderInfo resultInfo = new OrderInfo(
                        "GeekDubbo05",
                        "服务方异步方式之RpcContext.startAsync#" + id,
                        new BigDecimal(129));
                System.out.println(resultInfo);
                asyncContext.write(resultInfo);
            }
        });

        return null;
    }

    @Override
    public CompletableFuture<OrderInfo> queryDetailById(String id) {
        return CompletableFuture.supplyAsync(new Supplier<OrderInfo>() {
            @Override
            public OrderInfo get() {
                // 这里模拟执行一段耗时的业务逻辑
                sleepInner(1000);

                OrderInfo resultInfo = new OrderInfo(
                        "GeekDubbo05",
                        "服务方异步方式之CompletableFuture#" + id,
                        new BigDecimal(129));
                System.out.println(resultInfo);
                return resultInfo;
            }
        });
    }

    private static void sleepInner(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}