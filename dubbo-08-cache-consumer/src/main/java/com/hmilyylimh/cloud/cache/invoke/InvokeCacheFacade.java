package com.hmilyylimh.cloud.cache.invoke;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import com.hmilyylimh.cloud.facade.menu.MenuQueryFacade;
import com.hmilyylimh.cloud.facade.role.RoleQueryFacade;
import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class InvokeCacheFacade {

//    @DubboReference(cache = "jcache")

    // 引用下游查询角色信息列表的接口，添加 cache = lru 属性
    // @DubboReference(cache = "jcache")
    @DubboReference(methods = {@Method(
            name = "queryRoleList",
            parameters = {"cache.enable=true","cache.unique.nodes=id,name,sex"})}
    )
    private RoleQueryFacade roleQueryFacade;
    // 引用下游查询菜单信息列表的接口，添加 cache = lru 属性
    // @DubboReference(cache = "jcache")
    @DubboReference(methods = {@Method(
            name = "queryAuthorizedMenuList",
            parameters = {"cache.enable=true","cache.unique.nodes=id,name"})}
    )
    private MenuQueryFacade menuQueryFacade;
    // 引用下游查询菜单信息列表的接口，没有添加缓存属性
    @DubboReference
    private UserQueryFacade userQueryFacade;

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void invokeCache(){
        // 循环 3 次，模拟网关被 App 请求调用了 3 次
        for (int i = 1; i <= 1; i++) {
            int finalI = i;
            executorService.execute(() -> invokeCacheInner(finalI));
        }
    }

    public void invokeCacheInner(int i){
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

    public void invokeCache2(){
        // 循环 3 次，模拟网关被 App 请求调用了 3 次
        for (int i = 1; i <= 3; i++) {
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
}
