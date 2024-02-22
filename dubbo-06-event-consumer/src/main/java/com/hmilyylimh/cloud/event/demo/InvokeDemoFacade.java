package com.hmilyylimh.cloud.event.demo;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
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
    @DubboReference(
            /** 接口调研超时时间，1毫秒 **/
            timeout = 10000,
            /** 启动时不检查 DemoFacade 是否能正常提供服务 **/
            check = false,

            /** 为 DemoFacade 的 sayHello 方法设置事件通知机制 **/
            methods = {@Method(
                            name = "sayHello",
                            oninvoke = "eventNotifyService.onInvoke",
                            onreturn = "eventNotifyService.onReturn",
                            onthrow = "eventNotifyService.onThrow")}
    )
    private DemoFacade demoFacade;

    // 简单的一个 hello 方法，然后内部调用下游Dubbo接口 DemoFacade 的 sayHello 方法
    public String hello(String name){
        return demoFacade.sayHello(name);
    }
}
