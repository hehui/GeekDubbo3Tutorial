package com.hmilyylimh.cloud.filter.invoke;

import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoReq;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-12-17
 *
 */
@Component
public class InvokeDemoService {

    @DubboReference
    private UserQueryFacade userQueryFacade;

    public String invokeDemo(String name, String idNo) {
        SaveUserInfoReq req = new SaveUserInfoReq();
        req.setName(name);
        req.setIdNo(idNo);

        return userQueryFacade.saveUserInfo(req).toString();
    }
}