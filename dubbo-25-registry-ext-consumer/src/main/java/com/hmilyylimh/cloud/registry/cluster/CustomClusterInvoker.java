package com.hmilyylimh.cloud.registry.cluster;

import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker;

import java.util.List;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-19
 */
public class CustomClusterInvoker<T> extends MockClusterInvoker<T> {

    public CustomClusterInvoker(Directory<T> directory, Invoker<T> invoker) {
        super(directory, invoker);
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        List<Invoker<T>> invokers = getDirectory().list(invocation);
        for (Invoker<T> invoker : invokers) {
            System.out.println("invoker信息：");
            System.out.println(invoker.toString());
            System.out.println();
        }

        return super.invoke(invocation);
    }
}
