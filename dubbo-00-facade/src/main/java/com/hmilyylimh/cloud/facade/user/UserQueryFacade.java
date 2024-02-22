package com.hmilyylimh.cloud.facade.user;

import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoReq;
import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoResp;
import com.hmilyylimh.cloud.facade.user.bean.UserInfo;

/**
 * <h1>操作用户的接口。</h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-09-11
 *
 */
public interface UserQueryFacade {

    public String queryUser(String name);

    public UserInfo queryUserInfo(UserInfo userInfo);

    public SaveUserInfoResp saveUserInfo(SaveUserInfoReq req);
}