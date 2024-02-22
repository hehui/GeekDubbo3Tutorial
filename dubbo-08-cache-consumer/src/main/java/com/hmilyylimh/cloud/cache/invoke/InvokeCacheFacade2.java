//package com.hmilyylimh.cloud.cache.invoke;
//
//import com.hmilyylimh.cloud.facade.menu.MenuQueryFacade;
//import com.hmilyylimh.cloud.facade.role.RoleQueryFacade;
//import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.apache.dubbo.config.annotation.Method;
//
//import java.text.DecimalFormat;
//
///**
// * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
// *
// * @author hmilyylimh
// * ^_^
// * @version 0.0.1
// * ^_^
// * @date 2022-11-15
// */
//public class InvokeCacheFacade2 {
//
//    // 引用下游查询角色信息列表的接口
//    @DubboReference
//    private RoleQueryFacade roleQueryFacade;
//    // 引用下游查询菜单信息列表的接口
//    @DubboReference
//    private MenuQueryFacade menuQueryFacade;
//    // 引用下游查询菜单信息列表的接口
//    @DubboReference
//    private UserQueryFacade userQueryFacade;
//
//    public void invokeCache(){
//        // 循环 3 次，模拟网关被 App 请求调用了 3 次
//        for (int i = 1; i <= 3; i++) {
//            // 查询角色信息列表
//            String roleRespMsg = null;
//            // 检查有没有缓存角色信息列表的数据
//            if (hasCachedQueryRoleList("Geek")) {
//                // 有缓存则直接使用
//                roleRespMsg = queryCachedQueryRoleList("Geek");
//            } else {
//                // 没缓存则继续调用远程
//                roleRespMsg = roleQueryFacade.queryRoleList("Geek");
//                if (roleRespMsg != null) {
//                    // 拿到远程结果后再存储到缓存中
//                    cacheQueryRoleListResult(roleRespMsg);
//                }
//            }
//
//            // 查询菜单信息列表
//            String menuRespMsg = null;
//            // 检查有没有缓存菜单信息列表的数据
//            if (hasCachedQueryAuthorizedMenuList("Geek")) {
//                // 有缓存则直接使用
//                menuRespMsg = queryCachedQueryAuthorizedMenuList("Geek");
//            } else {
//                // 没缓存则继续调用远程
//                menuRespMsg = menuQueryFacade.queryAuthorizedMenuList("Geek");
//                if (menuRespMsg != null) {
//                    // 拿到远程结果后再存储到缓存中
//                    cacheQueryAuthorizedMenuListResult(roleRespMsg);
//                }
//            }
//
//            // 查询登录用户简情
//            String userRespMsg = userQueryFacade.queryUser("Geek");
//
//            // 打印远程调用的结果，看看是走缓存还是走远程
//            String idx = new DecimalFormat("00").format(i);
//            System.out.println("第 "+ idx + " 次调用【角色信息列表】结果为: " + roleRespMsg);
//            System.out.println("第 "+ idx + " 次调用【菜单信息列表】结果为: " + menuRespMsg);
//            System.out.println("第 "+ idx + " 次调用【登录用户简情】结果为: " + userRespMsg);
//            System.out.println();
//        }
//    }
//}
