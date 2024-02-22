package com.hmilyylimh.cloud.auth.invoke;

import com.hmilyylimh.cloud.facade.pay.PayAccountFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-15
 */
@Component
public class InvokeAuthFacade {

    // 引用下游支付账号查询的接口
//    @DubboReference(timeout = 10000, methods = {@Method(
//            name = "queryPayAccount",
//            parameters = {
//                    "auth.token", "123456789"
//            })})
    @DubboReference(timeout = 10000, methods = {@Method(
            name = "queryPayAccount",
            parameters = {
                    "auth.rsa.private.secret", "queryPayAccoun_privateSecret"
            })})
    private PayAccountFacade payAccountFacade;

    public void invokeAuth(){
        String respMsg = payAccountFacade.queryPayAccount("Geek");

        System.out.println(respMsg);
    }
}
