package com.hmilyylimh.cloud.validation.impl;

import com.hmilyylimh.cloud.facade.valid.ValidationFacade;
import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-13
 */
//@DubboService(validation = "true")
@DubboService
@Component
public class ValidationFacadeImpl implements ValidationFacade {

    @Override
    public String validateUser(ValidateUserInfo userInfo) {
        // 这里就象征性的模拟下业务逻辑
        String retMsg = "Ret: "
                + userInfo.getId()
                + "," + userInfo.getName()
                + "," + userInfo.getSex();
        System.out.println(retMsg);
        return retMsg;
    }
}
