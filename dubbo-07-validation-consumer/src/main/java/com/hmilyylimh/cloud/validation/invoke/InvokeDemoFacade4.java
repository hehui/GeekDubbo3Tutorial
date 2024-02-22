package com.hmilyylimh.cloud.validation.invoke;

import com.hmilyylimh.cloud.facade.valid.ValidationFacade;
import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;
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
 * @date 2022-09-04
 */
//@Component
public class InvokeDemoFacade4 {

    @DubboReference(stub = "com.hmilyylimh.cloud.validation.invoke.ValidationFacadeStub")
    private ValidationFacade validationFacade;

    // 一个简单的触发调用下游 ValidationFacade.validateUser 的方法
    public String invokeValidate(String id, String name, String sex) {
        return validationFacade.validateUser(new ValidateUserInfo(id, name, sex));
    }
}
