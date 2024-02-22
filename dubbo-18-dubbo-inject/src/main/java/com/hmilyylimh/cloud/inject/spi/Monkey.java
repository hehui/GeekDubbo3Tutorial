package com.hmilyylimh.cloud.inject.spi;

import org.apache.dubbo.common.URL;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-07
 */
public class Monkey implements Animal{

    @Override
    public String eat(URL url) {
        return "猴子吃香蕉";
    }
}
