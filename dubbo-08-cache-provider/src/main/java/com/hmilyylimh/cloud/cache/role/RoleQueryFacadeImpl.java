package com.hmilyylimh.cloud.cache.role;

import com.hmilyylimh.cloud.cache.util.TimeUtils;
import com.hmilyylimh.cloud.facade.role.RoleQueryFacade;
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
public class RoleQueryFacadeImpl implements RoleQueryFacade {

    @Override
    public String queryRoleList(String userId) {
        String result = String.format(TimeUtils.now() + ": Hello %s, 已查询该用户【角色列表信息】", userId);
        System.out.println(result);
        return result;
    }
}
