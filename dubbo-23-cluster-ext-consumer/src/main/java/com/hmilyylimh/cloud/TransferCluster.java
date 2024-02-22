package com.hmilyylimh.cloud;

import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;

public class TransferCluster implements Cluster {

    // 返回自定义的 Invoker 调用器
    @Override
    public <T> Invoker<T> join(Directory<T> directory, boolean buildFilterChain) throws RpcException {
        return new TransferClusterInvoker<T>(directory);
    }
}