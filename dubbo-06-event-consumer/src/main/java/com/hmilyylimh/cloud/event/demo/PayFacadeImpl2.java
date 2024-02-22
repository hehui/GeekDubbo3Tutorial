//package com.hmilyylimh.cloud.event.demo;
//
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.apache.dubbo.config.annotation.DubboService;
//import org.apache.dubbo.config.annotation.Method;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
// *
// * @author hmilyylimh
// * ^_^
// * @version 0.0.1
// * ^_^
// * @date 2022-09-04
// */
//@DubboService
//@Component
//public class PayFacadeImpl2 implements PayFacade, FrameworkNotifyService {
//
//    // 商品支付功能：一个大方法
//    @Override
//    public PayResp recvPay(PayReq req){
//        // 支付核心业务逻辑处理
//        method1();
//
//        // 返回支付结果
//        return buildSuccResp();
//    }
//
//    // 调用之前
//    @Override
//    void onInvoke(Request req){
//        System.out.println("[事件通知][调用之前] onInvoke 执行.");
//    }
//
//    // 调用之后
//    @Override
//    void onReturn(Response resp, Request req){
//        System.out.println("[事件通知][调用之后] onReturn 执行.");
//        // 埋点已支付的商品信息
//        method2();
//        // 发送支付成功短信给用户
//        method3();
//        // 通知物流派件
//        method4();
//    }
//
//    // 调用异常
//    @Override
//    void onThrow(Throwable ex, Request req){
//        System.out.println("[事件通知][调用异常] onThrow 执行.");
//    }
//
//    @Override
//    public OtherPayResp otherPay(OtherPayReq req){
//        // 此处省略若干行代码
//
//        // 返回支付结果
//        return buildOtherPayResp();
//    }
//}
