package com.hmilyylimh.cloud.validation.invoke;

import com.hmilyylimh.cloud.facade.valid.ValidationFacade;
import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;
import org.apache.dubbo.common.utils.StringUtils;

public class ValidationFacadeStub implements ValidationFacade {

    private final ValidationFacade validationFacade;

    public ValidationFacadeStub(ValidationFacade validationFacade) {
        this.validationFacade = validationFacade;
    }

    @Override
    public String validateUser(ValidateUserInfo userInfo) {
        // 校验 id 属性必填
        if (StringUtils.isBlank(userInfo.getId())) {
            throw new RuntimeException("[stub] id 不能为空");
        }
        // 校验 name 属性的长度必须在 5~10 之间
        String name = userInfo.getName();
        if (StringUtils.isNotBlank(name) && (name.length() < 5 || name.length() > 10)) {
            throw new RuntimeException("[stub] name 必须在 5~10 个长度之间");
        }
        // 然后再调用下游接口
        return validationFacade.validateUser(userInfo);
    }
}