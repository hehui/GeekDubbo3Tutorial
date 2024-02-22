package com.hmilyylimh.cloud.generic.user;

import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import com.hmilyylimh.cloud.facade.user.bean.UserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-11
 */
@DubboService
@Component
public class UserQueryFacadeImpl implements UserQueryFacade {

    @Override
    public String queryUser(String name) {
        int localPort = RpcContext.getServerContext().getLocalPort();
        name +=  "(" + localPort + ")";
        System.out.println(name);
        return name;
    }

    @Override
    public UserInfo queryUserInfo(UserInfo userInfo) {
        int localPort = RpcContext.getServerContext().getLocalPort();
        System.out.println("recv, userInfo " + userInfo + ", port = " + localPort);

        // 针对入参做一些简单的赋值操作，模拟进行了业务逻辑处理
        userInfo.setId(userInfo.getId() + "(" + localPort + ")");
        userInfo.setName(userInfo.getName() + "(" + localPort + ")");
        userInfo.setSex(userInfo.getSex() + "(" + localPort + ")");

        System.out.println("resp, userInfo " + userInfo + ", port = " + localPort);
        return userInfo;
    }
}
