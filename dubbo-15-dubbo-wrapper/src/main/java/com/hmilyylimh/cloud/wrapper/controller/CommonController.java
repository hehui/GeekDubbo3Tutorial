//package com.hmilyylimh.cloud.wrapper.controller;
//
//import com.hmilyylimh.cloud.facade.demo.DemoFacade;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
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
//public class CommonController {
//
//    // 定义URL地址
//    @PostMapping("gateway/{className}/{mtdName}/{parameterTypeName}/request")
//    public String recvCommonRequest(@PathVariable String className,
//                                    @PathVariable String mtdName,
//                                    @PathVariable String parameterTypeName,
//                                    @RequestBody String reqBody) throws Exception {
//        // 公共的接收请求的入口
//        return commonInvoke(className, parameterTypeName, mtdName, reqBody);
//    }
//
//    /**
//     * <h2>统一入口的核心逻辑。</h2>
//     *
//     * @param className：下接口归属方法的全类名。
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
//        // 接着通过类对象的简称获取到对应的接口服务
//        Object cacheObj = SpringCtxUtils.getBean(clz);
//        // 然后通过接口方法名和接口方法参数
//        if (cacheObj.getClass().getName().equals(className)) {
//            // 来精准定位需要提供方接口服务中的哪个方法进行处理
//            if ("sayHello".equals(mtdName) && String.class.getName().equals(parameterTypeName)) {
//                // 真正的发起对源对象（被代理对象）的方法调用
//                return ((DemoFacade) cacheObj).sayHello(reqParamsStr);
//            } else if("say".equals(mtdName) && Void.class.getName().equals(parameterTypeName)){
//                // 真正的发起对源对象（被代理对象）的方法调用
//                return ((DemoFacade) cacheObj).say();
//            }
//            // 如果找不到的话，就抛出异常，提示方法不存在
//            throw new RuntimeException(String.join(".", className, mtdName) + " 的方法不存在");
//        }
//        // 如果找不到的话，就抛出异常，提示类不存在
//        throw new RuntimeException(className + " 类不存在");
//    }
//}