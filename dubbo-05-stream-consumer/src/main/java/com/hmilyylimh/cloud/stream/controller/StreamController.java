package com.hmilyylimh.cloud.stream.controller;

import com.hmilyylimh.cloud.facade.stream.StreamFacade;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
@RestController
public class StreamController implements Serializable {

    private static final long serialVersionUID = 7186019704954191369L;

    @DubboReference
    private StreamFacade streamFacade;

    @GetMapping("/serverStream")
    public String serverStream(){
        streamFacade.sayHelloServerStream("GeekDubbo05-服务端流式调用", new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println(now() + ", 客户端收到服务端数据： " + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println(now() + ", 流式调用 onCompleted 结束");
            }
        });
        return "serverStream finished";
    }

    @GetMapping("/clientStream")
    public String clientStream(){
        StreamObserver<String> stringStreamObserver = streamFacade.sayHelloClientStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println(now() + ", 客户端收到服务端数据： " + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println(now() + ", 流式调用 onCompleted 结束");
            }
        });
        stringStreamObserver.onNext("客户端向服务端发送第 1 个事件");
        stringStreamObserver.onNext("客户端向服务端发送第 2 个事件");
        stringStreamObserver.onNext("客户端向服务端发送第 3 个事件");
        stringStreamObserver.onCompleted();
        System.out.println(now() + ", 客户端向服务端发送完成。");
        return "clientStream finished";
    }

    private static String now() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
