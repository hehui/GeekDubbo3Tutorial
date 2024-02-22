package com.hmilyylimh.cloud.auth.config;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.log4j.MDC;

import java.util.Map;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-22
 */
@Activate(group = PROVIDER)
public class ProviderTokenFilter implements Filter {

    /** <h2>TOKEN 字段名</h2> **/
    public static final String TOKEN = "TOKEN";

    /** <h2>方法级别层面获取配置的 auth.enable 参数名</h2> **/
    public static final String KEY_AUTH_ENABLE = "auth.enable";

    /** <h2>方法级别层面获取配置的 auth.token 参数名</h2> **/
    public static final String KEY_AUTH_TOKEN = "auth.token";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 从方法层面获取 auth.enable 参数值
        String authEnable = invoker.getUrl().getMethodParameter
                (invocation.getMethodName(), KEY_AUTH_ENABLE);
        // 如果不需要开启 TOKEN 认证的话，则继续后面过滤器的调用
        if (!Boolean.TRUE.toString().equals(authEnable)) {
            return invoker.invoke(invocation);
        }

        // 能来到这里，说明需要进行 TOKEN 认证
        Map<String, Object> attachments = invocation.getObjectAttachments();
        String recvToken = attachments != null ? (String) attachments.get(TOKEN) : null;
        // 既然需要认证，如果收到的 TOKEN 为空，则直接抛异常
        if (StringUtils.isBlank(recvToken)) {
            throw new RuntimeException(
                    "Recv token is null or empty, path: " +
                     String.join(".", invoker.getInterface().getName(), invocation.getMethodName()));
        }

        // 从方法层面获取 auth.token 参数值
        String authToken = invoker.getUrl().getMethodParameter
                (invocation.getMethodName(), KEY_AUTH_TOKEN);
        // 既然需要认证，如果收到的 TOKEN 值和提供方配置的 TOKEN 值不一致的话，也直接抛异常
        if(!recvToken.equals(authToken)){
            throw new RuntimeException(
                    "Recv token is invalid, path: " +
                     String.join(".", invoker.getInterface().getName(), invocation.getMethodName()));
        }

        // 还能来到这，说明认证通过，继续后面过滤器的调用
        return invoker.invoke(invocation);
    }
}
