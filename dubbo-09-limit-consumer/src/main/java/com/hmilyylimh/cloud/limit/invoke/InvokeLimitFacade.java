package com.hmilyylimh.cloud.limit.invoke;

import com.hmilyylimh.cloud.facade.menu.MenuQueryFacade;
import com.hmilyylimh.cloud.facade.role.RoleQueryFacade;
import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-15
 */
@Component
public class InvokeLimitFacade {

    // 引用下游查询角色信息列表的接口
    @DubboReference(timeout = 10000, methods = {@Method(
            name = "queryRoleList",
            parameters = {
                    "qps.enable", "true",
                    "qps.value", "3"
            })})
    private RoleQueryFacade roleQueryFacade;
    // 引用下游查询菜单信息列表的接口
    @DubboReference(timeout = 10000)
    private MenuQueryFacade menuQueryFacade;
    // 引用下游查询菜单信息列表的接口
    @DubboReference
    private UserQueryFacade userQueryFacade;

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void invokeFilter(){
        // 循环 5 次，模拟网关被 App 请求调用了 5 次
        for (int i = 1; i <= 5; i++) {
            int idx = i;
            executorService.execute(() -> invokeCacheInner(idx));
        }
    }

    private void invokeCacheInner(int i){
        // 查询角色信息列表
        String roleRespMsg = roleQueryFacade.queryRoleList("Geek");
        // 查询菜单信息列表
        String menuRespMsg = menuQueryFacade.queryAuthorizedMenuList("Geek");
        // 查询登录用户简情
        String userRespMsg = userQueryFacade.queryUser("Geek");

        // 打印远程调用的结果，看看是走缓存还是走远程
        String idx = new DecimalFormat("00").format(i);
        System.out.println("第 "+ idx + " 次调用【角色信息列表】结果为: " + roleRespMsg);
        System.out.println("第 "+ idx + " 次调用【菜单信息列表】结果为: " + menuRespMsg);
        System.out.println("第 "+ idx + " 次调用【登录用户简情】结果为: " + userRespMsg);
        System.out.println();
    }
}
