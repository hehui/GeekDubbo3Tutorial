package com.hmilyylimh.cloud.registry.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.zookeeper.ZookeeperRegistry;
import org.apache.dubbo.remoting.zookeeper.ZookeeperTransporter;

/**
 * 尽量不做大的改动，继承了 ZookeeperRegistry，然后重写了注册与注销的方法
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-19
 */
public class RouteNoZkRegistry extends ZookeeperRegistry {

    public RouteNoZkRegistry(URL url, ZookeeperTransporter zookeeperTransporter) {
        super(url, zookeeperTransporter);
    }

    @Override
    public void doRegister(URL url) {
        super.doRegister(appendRouteGroup(url));
    }

    @Override
    public void doUnregister(URL url) {
        super.doUnregister(appendRouteGroup(url));
    }

    protected URL appendRouteGroup(URL url) {
        // routeNo 属性值，比如填上机器编号
        url = url.addParameter("routeNo", "M20221219");
        return url;
    }
}
