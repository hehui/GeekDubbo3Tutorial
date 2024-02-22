package com.hmilyylimh.cloud.registry.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.support.AbstractRegistryFactory;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;
import org.apache.dubbo.rpc.model.ApplicationModel;

/**
 * 摘抄源码 ZookeeperRegistryFactory，然后稍加改造了一番，重写了 createRegistry 方法
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-19
 */
public class RouteNoZkRegistryFactory extends AbstractRegistryFactory {

    private ZookeeperTransporter zookeeperTransporter;

    // for compatible usage
    public RouteNoZkRegistryFactory() {
        this(ApplicationModel.defaultModel());
    }

    public RouteNoZkRegistryFactory(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
        this.zookeeperTransporter = ZookeeperTransporter.getExtension(applicationModel);
    }

    @Override
    public Registry createRegistry(URL url) {
        return new RouteNoZkRegistry(url, zookeeperTransporter);
    }
}