package com.hmilyylimh.cloud.xml.demo;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-04
 */
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = String.format("Hello %s, I'm in 'dubbo-04-xml-boot-provider' project.", name);
        System.out.println(result);
        return result;
    }
}
