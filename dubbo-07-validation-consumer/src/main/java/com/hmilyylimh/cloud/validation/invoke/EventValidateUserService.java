package com.hmilyylimh.cloud.validation.invoke;

import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;

public interface EventValidateUserService {
    /**
     * 调用之前
     * @param userInfo
     */
    void onInvoke(ValidateUserInfo userInfo);

    /**
     * 有参数：调用之后
     * @param result 第一个参数 接收 [事件通知]服务接口的方法返回值类型保持一致
     * @param userInfo 第二个或者之后，与[事件通知]服务接口的方法入参保持一致
     */
    void onReturn(String result, ValidateUserInfo userInfo);

    /**
     * 抛异常
     * @param ex
     * @param userInfo
     */
    void onThrow(Throwable ex, ValidateUserInfo userInfo);
}