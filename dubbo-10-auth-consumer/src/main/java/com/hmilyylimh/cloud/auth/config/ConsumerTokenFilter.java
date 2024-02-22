package com.hmilyylimh.cloud.auth.config;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-22
 */
@Activate(group = CONSUMER)
public class ConsumerTokenFilter implements Filter {

    /** <h2>方法级别层面获取配置的 auth.token 参数名</h2> **/
    public static final String KEY_AUTH_TOKEN = "auth.token";

    /** <h2>TOKEN 字段名</h2> **/
    public static final String TOKEN = "TOKEN";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 从方法层面获取 auth.token 参数值
        String authToken = invoker.getUrl().getMethodParameter
                    (invocation.getMethodName(), KEY_AUTH_TOKEN);
        // authToken 不为空的话则设置到请求对象中
        if (StringUtils.isNotBlank(authToken)) {
            invocation.getObjectAttachments().put(TOKEN, authToken);
        }

        // 继续后面过滤器的调用
        return invoker.invoke(invocation);
    }
}