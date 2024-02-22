package com.hmilyylimh.cloud.limit.user;

import com.hmilyylimh.cloud.limit.util.TimeUtils;
import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import com.hmilyylimh.cloud.facade.user.bean.UserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-18
 */
@DubboService
@Component
public class UserQueryFacadeImpl implements UserQueryFacade {

    @Override
    public String queryUser(String name) {
        String result = String.format(TimeUtils.now() + ": Hello %s, 已查询【用户简单信息】", name);
        System.out.println(result);
        return result;
    }

    @Override
    public UserInfo queryUserInfo(UserInfo userInfo) {
        return null;
    }
}
