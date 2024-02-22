package com.hmilyylimh.cloud.generic.controller;

import com.alibaba.fastjson.JSON;
import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import com.hmilyylimh.cloud.facade.user.bean.UserInfo;
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
public class UserController {
    // 响应码为成功时的值
    public static final String SUCC = "000000";

    // 定义访问下游查询用户服务的字段
    @DubboReference
    private UserQueryFacade userQueryFacade;

    // 定义URL地址
    @PostMapping("/queryUserInfo")
    public String queryUserInfo(@RequestBody QueryUserInfoReq req){
        // 将入参的req转为下游方法的入参对象，并发起远程调用
        QueryUserInfoResp resp =
                QueryUserInfoResp.valueOf(userQueryFacade.queryUserInfo(convertReq(req)));

        // 判断响应对象的响应码，不是成功的话，则组装失败响应
        if(!SUCC.equals(resp.getRespCode())){
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
    private UserInfo convertReq(QueryUserInfoReq req) {
        return null;
    }

    private class QueryUserInfoReq {
    }

    private static class QueryUserInfoResp {
        public static QueryUserInfoResp valueOf(UserInfo queryUserInfo) {
            return null;
        }

        public String getRespCode() {
            return null;
        }
    }

    private static class RespUtils {
        public static String fail(QueryUserInfoResp resp) {
            return null;
        }

        public static String ok(QueryUserInfoResp resp) {
            return null;
        }
    }
}
