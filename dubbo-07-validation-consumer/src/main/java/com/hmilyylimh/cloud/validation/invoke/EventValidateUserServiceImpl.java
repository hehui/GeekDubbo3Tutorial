package com.hmilyylimh.cloud.validation.invoke;

import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component("eventValidateUserService")
public class EventValidateUserServiceImpl implements EventValidateUserService {
    // 调用之前
    @Override
    public void onInvoke(ValidateUserInfo userInfo){
        // 校验 id 属性必填
        if (StringUtils.isBlank(userInfo.getId())) {
            throw new RuntimeException("[onInvoke] id 不能为空");
        }
        // 校验 name 属性的长度必须在 5~10 之间
        String name = userInfo.getName();
        if (StringUtils.isNotBlank(name) && (name.length() < 5 || name.length() > 10)) {
            throw new RuntimeException("[onInvoke] name 必须在 5~10 个长度之间");
        }
    }

    // 调用之后
    @Override
    public void onReturn(String result, ValidateUserInfo userInfo){
        System.out.println("[事件通知][调用之后] onReturn 执行.");
    }

    // 调用异常
    @Override
    public void onThrow(Throwable ex, ValidateUserInfo userInfo){
        System.out.println("[事件通知][调用异常] onThrow 执行.");
        if(ex != null){
            throw new RuntimeException(ex);
        }
    }
}