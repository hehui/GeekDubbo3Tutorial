package com.hmilyylimh.cloud.facade.stream;

import org.apache.dubbo.common.stream.StreamObserver;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-04
 */
public interface StreamFacade {

    public String sayHello(String name);

    public void sayHelloServerStream(String name, StreamObserver<String> response);

    StreamObserver<String> sayHelloClientStream(StreamObserver<String> response);
}