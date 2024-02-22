package com.hmilyylimh.cloud.generic.controller;

import com.alibaba.fastjson.JSON;
import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-25
 */
@RestController
public class UserController2 {
    // 响应码为成功时的值
    public static final String SUCC = "000000";

    // 定义访问下游查询用户服务的字段
    @DubboReference
    private UserQueryFacade userQueryFacade;

    // 定义URL地址
    @PostMapping("/queryUserInfo")
    public String queryUserInfo(@RequestBody QueryUserInfoReq req) throws Exception{
        // 调用公共方法
        return commonInvoke(userQueryFacade, "queryUserInfo", req);
    }

    /**
     * <h2>模拟公共的远程调用方法.</h2>
     *
     * @param reqObj：下游的接口的实例对象，即通过 @DubboReference 得到的对象。
     * @param mtdName：下游接口的方法名。
     * @param reqParams：需要请求到下游的数据。
     * @return 直接结果数据。
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String commonInvoke(Object reqObj, String mtdName, Object reqParams) throws InvocationTargetException, IllegalAccessException {
        // 通过反射找到 reqObj(例：userQueryFacade) 中的 mtdName(例：queryUserInfo) 方法
        Method reqMethod = ReflectionUtils.findMethod(reqObj.getClass(), mtdName);
        // 并设置查找出来的方法可被访问
        ReflectionUtils.makeAccessible(reqMethod);

        // 通过序列化工具将 reqParams 序列化为字符串格式
        String reqParamsStr = JSON.toJSONString(reqParams);
        // 然后再将 reqParamsStr 反序列化为下游对象格式，并反射调用 invoke 方法
        Object resp =  reqMethod.invoke(reqObj, JSON.parseObject(reqParamsStr, reqMethod.getParameterTypes()[0]));

        // 判断响应对象的响应码，不是成功的话，则组装失败响应
        if(resp == null || !SUCC.equals(OgnlUtils.getValue(resp, "respCode"))){
            return RespUtils.fail(resp);
        }
        // 如果响应码为成功的话，则组装成功响应
        return RespUtils.ok(resp);
    }

    //////////////////////////////////////////////////////////////////////////////////
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已

    private static class OgnlUtils {
        public static String getValue(Object resp, String respCode) {
            return null;
        }
    }

    private static class RespUtils {
        public static String fail(Object resp) {
            return null;
        }

        public static String ok(Object resp) {
            return null;
        }
    }

    private class QueryUserInfoReq {
    }
}