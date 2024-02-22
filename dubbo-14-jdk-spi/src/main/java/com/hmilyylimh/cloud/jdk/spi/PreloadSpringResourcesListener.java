package com.hmilyylimh.cloud.jdk.spi;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-29
 */
public class PreloadSpringResourcesListener implements ApplicationStartedListener {

    @Override
    public void onCompleted() {
        System.out.println("预加载 Spring 框架的一些资源, " + this);
    }
}
