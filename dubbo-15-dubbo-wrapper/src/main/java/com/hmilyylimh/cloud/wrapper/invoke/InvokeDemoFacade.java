package com.hmilyylimh.cloud.wrapper.invoke;

import com.hmilyylimh.cloud.wrapper.demo.DemoFacadeImpl;
import org.apache.dubbo.common.bytecode.Wrapper;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-02
 */
public class InvokeDemoFacade {

    public static void main(String[] args) throws Exception {
        // 创建一个源对象（即被代理类）
        DemoFacadeImpl demoFacade = new DemoFacadeImpl();

        // 使用 Wrapper 机制获取一个继承  Wrapper 的代理类
        final Wrapper wrapper = Wrapper.getWrapper(demoFacade.getClass());

        // 使用生成的 wrapper 代理类调用通用的 invokeMethod 方法获取结果
        Object result = wrapper.invokeMethod(
                demoFacade,
                "sayHello",
                new Class[]{String.class},
                new Object[]{"Geek"}
        );
        // 然后打印调用的结果
        System.out.println("wrapper调用结果为：" + result);
    }
}
