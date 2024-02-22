package com.hmilyylimh.cloud.auth.pay;

import com.hmilyylimh.cloud.facade.pay.PayAccountFacade;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-22
 */
//@DubboService(methods = {@Method(
//        name = "queryPayAccount",
//        parameters = {
//                "auth.token", "123456789",
//                "auth.enable", "true"
//        })}
//)
@DubboService(methods = {@Method(
        name = "queryPayAccount",
        parameters = {
                "auth.rsa.public.secret", "queryPayAccoun_publicSecret",
                "auth.rsa.enable", "true"
        })}
)
@Component
public class PayAccountFacadeImpl implements PayAccountFacade {

    @Override
    public String queryPayAccount(String userId) {
        String result = String.format(now() + ": Hello %s, 已查询该用户的【银行账号信息】", userId);
        System.out.println(result);
        return result;
    }

    private static String now() {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS").format(new Date());
    }
}
