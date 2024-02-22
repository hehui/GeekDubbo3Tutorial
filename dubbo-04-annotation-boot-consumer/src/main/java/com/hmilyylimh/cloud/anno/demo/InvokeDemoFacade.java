package com.hmilyylimh.cloud.anno.demo;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-04
 */
@Component
public class InvokeDemoFacade {

    @Autowired
    @DubboReference
    private DemoFacade demoFacade;

    public String hello(String name){
        return demoFacade.sayHello(name);
    }
}
