package com.hmilyylimh.cloud.wrapper.custom;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.apache.dubbo.common.bytecode.NoSuchMethodException;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-02
 */
public class $DemoFacadeCustomInvoker extends CustomInvoker {

    @Override
    public Object invokeMethod(Object instance, String mtdName, Class<?>[] types, Object[] args) throws NoSuchMethodException {
        if ("sayHello".equals(mtdName)) {
            return ((DemoFacade) instance).sayHello(String.valueOf(args[0]));
        } else if ("say".equals(mtdName)) {
            return ((DemoFacade) instance).say();
        }

        throw new NoSuchMethodException("Method [" + mtdName + "] not found.");
    }
}