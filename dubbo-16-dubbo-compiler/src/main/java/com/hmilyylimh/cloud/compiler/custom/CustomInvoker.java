package com.hmilyylimh.cloud.compiler.custom;

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
public abstract class CustomInvoker {

    public Object invokeMethod(Object instance, String mtdName, Class<?>[] types, Object[] args) throws NoSuchMethodException {
        throw new NoSuchMethodException("Method [" + mtdName + "] not found.");
    }
}
