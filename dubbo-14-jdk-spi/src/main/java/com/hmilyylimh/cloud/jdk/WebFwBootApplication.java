package com.hmilyylimh.cloud.jdk;

import com.hmilyylimh.cloud.jdk.spi.ApplicationStartedListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-29
 */
public class WebFwBootApplication {

    // web-fw.jar 插件的启动入口
    public static void run(Class<?> primarySource, String... args) {
        // 开始启动中，此处省略若干行代码...
        // 省略部分代码，此处省略若干行代码...
        // 环境已准备好，此处省略若干行代码...
        // 上下文已实例化，此处省略若干行代码...
        // 上下文已准备好，此处省略若干行代码

        // 应用成功启动
        onCompleted();

        // 应用已准备好，此处省略若干行代码
    }

    // 应用成功启动时刻，提供一个扩展口子
    private static void onCompleted() {
        // 加载 ApplicationStartedListener 接口的所有实现类
        ServiceLoader<ApplicationStartedListener> loader = ServiceLoader.load(ApplicationStartedListener.class);
        // 遍历 ApplicationStartedListener 接口的所有实现类，并调用里面的 onCompleted 方法
        Iterator<ApplicationStartedListener> it = loader.iterator();
        while (it.hasNext()){
            // 获取其中的一个实例，并调用 onCompleted 方法
            ApplicationStartedListener instance = it.next();
            instance.onCompleted();
        }
    }

    public static void main(String[] args) {
        // 模拟进行 3 次调用 load 方法并传入同一个接口
        for (int i = 0; i < 3; i++) {
            // 加载 ApplicationStartedListener 接口的所有实现类
            ServiceLoader<ApplicationStartedListener> loader = ServiceLoader.load(ApplicationStartedListener.class);
            // 遍历 ApplicationStartedListener 接口的所有实现类，并调用里面的 onCompleted 方法
            Iterator<ApplicationStartedListener> it = loader.iterator();
            while (it.hasNext()){
                // 获取其中的一个实例，并调用 onCompleted 方法
                ApplicationStartedListener instance = it.next();
                instance.onCompleted();
            }
        }
    }
}
