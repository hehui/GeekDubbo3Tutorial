package com.hmilyylimh.cloud.filter.user;

import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoReq;
import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoResp;
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
 * @date 2022-12-18
 */
@DubboService
@Component
public class UserQueryFacadeImpl implements UserQueryFacade {
    @Override
    public SaveUserInfoResp saveUserInfo(SaveUserInfoReq req) {
        // 保存用户信息的核心逻辑
        doSaveUserInfo(req);

        // 返回成功响应
        return buildSuccResp();
    }

    @Override
    public String queryUser(String name) {
        return null;
    }

    @Override
    public UserInfo queryUserInfo(UserInfo userInfo) {
        return null;
    }

    private SaveUserInfoResp buildSuccResp() {
        return new SaveUserInfoResp();
    }

    private void doSaveUserInfo(SaveUserInfoReq req) {
        System.out.println("doSaveUserInfo入参打印：" + req);
    }
}
