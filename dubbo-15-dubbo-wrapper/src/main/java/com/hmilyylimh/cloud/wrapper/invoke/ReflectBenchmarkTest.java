package com.hmilyylimh.cloud.wrapper.invoke;

import com.hmilyylimh.cloud.wrapper.spi.CustomSpi;
import org.apache.dubbo.common.bytecode.Wrapper;

import java.lang.reflect.Method;

public class ReflectBenchmarkTest {
    public static void main(String[] args) {
        int size = 1000000;
        // 循环正常创建类调用某个方法，并打印耗时的时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            normalInvoke();
        }
        long end = System.currentTimeMillis();
        System.out.println("正常调用耗时为：" + (end - start) + " 毫秒");
        
        // 循环反射创建类调用某个方法，并打印耗时的时间
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            reflectInvoke();
        }
        end = System.currentTimeMillis();
        System.out.println("反射调用耗时为：" + (end - start) + " 毫秒");

        // 循环进行Wrapper调用，并打印耗时的时间
        CustomSpi customSpi = new CustomSpi();
        final Wrapper wrapper = Wrapper.getWrapper(customSpi.getClass());
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            wrapperInvoke(wrapper, customSpi);
        }
        end = System.currentTimeMillis();
        System.out.println("Wrapper调用耗时为：" + (end - start) + " 毫秒");
    }
    // 正常创建对象，并调用对象的方法
    public static void normalInvoke(){
        CustomSpi customSpi = new CustomSpi();
        customSpi.getDefaultPort();
    }
    // 反射创建对象，并反射调用对象的方法
    public static void reflectInvoke(){
        try {
            Class<?> clz = Class.forName("com.hmilyylimh.cloud.wrapper.spi.CustomSpi");
            Object o = clz.newInstance();
            Method method = clz.getDeclaredMethod("getDefaultPort");
            method.invoke(o);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    // 反射创建对象，并反射调用对象的方法
    public static void wrapperInvoke(Wrapper wrapper, Object reqObj){
        try {
            // 使用生成的 wrapper 代理类调用通用的 invokeMethod 方法获取结果
            wrapper.invokeMethod(
                    reqObj,
                    "getDefaultPort",
                    new Class[]{},
                    new Object[]{}
            );
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}