package com.hmilyylimh.cloud.protocol.config.ext;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.UrlUtils;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.model.ScopeModelUtil;

import java.util.ArrayList;
import java.util.List;

import static org.apache.dubbo.common.constants.RegistryConstants.REGISTRY_KEY;
import static org.apache.dubbo.common.constants.RegistryConstants.REGISTRY_PROTOCOL;
import static org.apache.dubbo.registry.Constants.DEFAULT_REGISTRY;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-31
 */
public class OverrideProtocolWrapper implements Protocol {

    private final Protocol protocol;
    private Registry registry;

    private static final List<URL> UN_REGISTRY_URL_LIST = new ArrayList<>();

    public OverrideProtocolWrapper(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        // 如果是注册协议的话，那么就先注册一个 override 到 zk 上，表示禁用接口被调用
        if (UrlUtils.isRegistry(invoker.getUrl())) {
            if (registry == null) {
                registry = getRegistry(invoker);
            }

            // 注册 override url
            doRegistryOverrideUrl(invoker);
        }

        // 如果不是注册协议的话，原来该怎么调用还是接着怎么进行下一步调用
        return this.protocol.export(invoker);
    }

    private <T> void doRegistryOverrideUrl(Invoker<T> invoker) {
        URL originalProviderUrl = getProviderUrl(invoker);
        UN_REGISTRY_URL_LIST.add(originalProviderUrl);

        OverrideBean override = new OverrideBean();
        override.setAddress(originalProviderUrl.getAddress());
        override.setService(originalProviderUrl.getServiceKey());
        override.setEnabled(true);
        override.setParams("disabled=true");

        registry.register(override.toUrl());
    }

    private Registry getRegistry(Invoker<?> originInvoker) {
        URL registryUrl = originInvoker.getUrl();
        if (REGISTRY_PROTOCOL.equals(registryUrl.getProtocol())) {
            String protocol = registryUrl.getParameter(REGISTRY_KEY, DEFAULT_REGISTRY);
            registryUrl = registryUrl.setProtocol(protocol).removeParameter(REGISTRY_KEY);
        }

        RegistryFactory registryFactory = ScopeModelUtil.getExtensionLoader
                (RegistryFactory.class, registryUrl.getScopeModel()).getAdaptiveExtension();
        return registryFactory.getRegistry(registryUrl);
    }

    private URL getProviderUrl(final Invoker<?> originInvoker) {
        return (URL) originInvoker.getUrl().getAttribute("export");
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        return protocol.refer(type, url);
    }

    @Override
    public int getDefaultPort() {
        return protocol.getDefaultPort();
    }

    @Override
    public void destroy() {
        protocol.destroy();
    }


    public void online() {
        System.out.println("开始注册");
        for (URL url : UN_REGISTRY_URL_LIST) {
            OverrideBean override = new OverrideBean();
            override.setAddress(url.getAddress());
            override.setService(url.getServiceKey());
            override.setEnabled(true);
            override.setParams("disabled=false");
            registry.register(override.toUrl());

            registry.register(url);
        }
    }
}
