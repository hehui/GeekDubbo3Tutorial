package com.hmilyylimh.cloud.stream.impl;

import com.hmilyylimh.cloud.facade.stream.StreamFacade;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class StreamFacadeImpl implements StreamFacade {

    @Override
    public String sayHello(String name) {
        String result = String.format("Hello %s, I'm in 'dubbo-05-stream-provider' project.", name);
        System.out.println(result);
        return result;
    }

    @Override
    public void sayHelloServerStream(String name, StreamObserver<String> response) {
        response.onNext("服务端收到【" + name + "】后，返回第 1 个事件结果");
        response.onNext("服务端收到【" + name + "】后，返回第 2 个事件结果");
        response.onNext("服务端收到【" + name + "】后，返回第 3 个事件结果");
        response.onCompleted();
    }

    @Override
    public StreamObserver<String> sayHelloClientStream(StreamObserver<String> response) {
        return new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                response.onNext(data + ", 服务端已处理该事件。");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println(now() + ", 流式调用 onCompleted 结束");
            }
        };
    }

    private static String now() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
