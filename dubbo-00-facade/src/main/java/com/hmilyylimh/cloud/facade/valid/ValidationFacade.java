package com.hmilyylimh.cloud.facade.valid;

import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;

/**
 * <h1>校验接口。</h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-11-13
 *
 */
public interface ValidationFacade {

    public String validateUser(ValidateUserInfo userInfo);
}
