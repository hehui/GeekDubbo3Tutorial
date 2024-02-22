package com.hmilyylimh.cloud.arch.demo;

import com.hmilyylimh.cloud.arch.util.TimeUtils;
import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-04
 */
@Component
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public String sayHello(String name) {
        TimeUtils.sleep(5000);
        String result = String.format("Hello %s, I'm in 'dubbo-12-arch-provider' project.", name);
        System.out.println(result);
        return result;
    }

    @Override
    public String say() {
        TimeUtils.sleep(5000);
        String result = String.format("Hello %s, I'm in 'dubbo-12-arch-provider' project.", "defaultSayMethod");
        System.out.println(result);
        return result;
    }
}
