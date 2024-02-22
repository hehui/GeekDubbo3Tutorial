package com.hmilyylimh.cloud.jdk;

import java.nio.file.spi.FileSystemProvider;
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
 * @date 2022-09-12
 */
public class Dubbo14JdkSpiApplication {

    public static void main(String[] args) {
        // 模拟 app-web 调用 web-fw 框架启动整个后台应用
        WebFwBootApplication.run(Dubbo14JdkSpiApplication.class, args);
    }
}
