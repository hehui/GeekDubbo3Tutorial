//package com.hmilyylimh.cloud.wrapper.controller;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Method;
//
///**
// * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
// *
// * @author hmilyylimh
// * ^_^
// * @version 0.0.1
// * ^_^
// * @date 2022-09-25
// */
//@RestController
//public class CommonController2 {
//
//    // 定义URL地址
//    @PostMapping("gateway/{className}/{mtdName}/{parameterTypeName}/request")
//    public String recvCommonRequest(@PathVariable String className,
//                                    @PathVariable String mtdName,
//                                    @PathVariable String parameterTypeName,
//                                    @RequestBody String reqBody) throws Exception {
//        // 统一的接收请求的入口
//        return commonInvoke(className, parameterTypeName, mtdName, reqBody);
//    }
//
//    /**
//     * <h2>统一入口的核心逻辑。</h2>
//     *
//     * @param className：接口归属方法的全类名。
//     * @param mtdName：接口的方法名。
//     * @param parameterTypeName：接口的方法入参的全类名。
//     * @param reqParamsStr：请求数据。
//     * @return 接口方法调用的返回信息。
//     * @throws Exception
//     */
//    public static String commonInvoke(String className,
//                                      String mtdName,
//                                      String parameterTypeName,
//                                      String reqParamsStr) throws Exception {
//        // 通过反射机制可以获取接口类名对应的类对象
//        Class<?> clz = Class.forName(className);
//
//        // 接着通过类对象的简称获取到对应的接口服务的【代理对象】
//        // 相当于不同的 clz 就会获取不同的代理对象，各个代理对象代理的源对象都不一样的
//        ProxyInvoker proxyInvoker = SpringCtxUtils.getBean(clz);
//
//        // 【代理对象】调用自身的统一方法，然后内部会去识别方法名、方法参数调用不同的方法
//        return proxyInvoker.invoke(clz, mtdName, parameterTypeName, reqParamsStr);
//    }
//
//    public class ProxyInvoker$1 extends ProxyInvoker {
//        // 暴露的统一被调用的方法
//        public Object invoke(Class<?> clz,
//                             String mtdName,
//                             String parameterTypeName,
//                             String reqParamsStr){
//            // 通过反射找到方法对应的 Method 对象
//            Method method = clz.getDeclaredMethod(mtdName, Class.forName(parameterTypeName));
//            method.setAccessible(true);
//
//            // 反射调用
//            return method.invoke(getSourceTarget(), reqParamsStr);
//        }
//    }
//}