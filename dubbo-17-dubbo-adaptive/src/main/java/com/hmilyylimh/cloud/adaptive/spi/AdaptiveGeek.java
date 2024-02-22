package com.hmilyylimh.cloud.adaptive.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-04
 */
//@Adaptive
public class AdaptiveGeek implements Geek {
    @Override
    public String getCourse(URL url) {
        return "17｜Adaptive 适配：Dubbo的Adaptive特殊在哪里？";
    }

    @Override
    public String getCourse2(URL url) {
        return "17｜Adaptive 适配：Dubbo的Adaptive特殊在哪里？(2)";
    }
}
