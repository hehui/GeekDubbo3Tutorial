package com.hmilyylimh.cloud;

import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.support.FailoverClusterInvoker;

import java.util.List;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-10-25
 */
public class TransferClusterInvoker<T> extends FailoverClusterInvoker<T> {

    public TransferClusterInvoker(Directory<T> directory) {
        super(directory);
    }

    @Override
    public Result doInvoke(Invocation invocation, List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
        try {
            // System.out.println("TransferClusterInvoker start.");
            // 先完全按照父类的业务逻辑调用处理
            return super.doInvoke(invocation, invokers, loadbalance);
        } catch (RpcException e) {
            // 当调用发现无提供者异常描述信息时则向转发服务发起调用
            if (e.getMessage().toLowerCase().contains("no provider available")){
                // TODO 从 invocation 中拿到所有的参数，然后再处理调用转发服务的逻辑
                return doTransferInvoke(invocation);
            }
            throw e;
        }
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

    private Result doTransferInvoke(Invocation invocation) {
        return null;
    }
}
