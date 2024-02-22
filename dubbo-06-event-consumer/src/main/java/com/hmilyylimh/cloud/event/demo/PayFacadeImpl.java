//package com.hmilyylimh.cloud.event.demo;
//
//import com.hmilyylimh.cloud.facade.demo.DemoFacade;
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
//public class PayFacadeImpl implements PayFacade {
//
//    @Autowired
//    @DubboReference(
//            /** 为 DemoRemoteFacade 的 sayHello 方法设置事件通知机制 **/
//            methods = {@Method(
//                    name = "sayHello",
//                    oninvoke = "eventNotifyService.onInvoke",
//                    onreturn = "eventNotifyService.onReturn",
//                    onthrow = "eventNotifyService.onThrow")}
//    )
//    private DemoRemoteFacade demoRemoteFacade;
//
//    // 商品支付功能：一个大方法
//    @Override
//    public PayResp recvPay(PayReq req){
//        // 支付核心业务逻辑处理
//        method1();
//
//        // 埋点已支付的商品信息
//        method2();
//
//        // 发送支付成功短信给用户
//        method3();
//
//        // 通知物流派件
//        method4();
//
//        // 返回支付结果
//        return buildSuccResp();
//    }
//
//    private void method1() {
//        // 省略其他一些支付核心业务逻辑处理代码
//        demoRemoteFacade.sayHello(buildSayHelloReq());
//    }
//}
