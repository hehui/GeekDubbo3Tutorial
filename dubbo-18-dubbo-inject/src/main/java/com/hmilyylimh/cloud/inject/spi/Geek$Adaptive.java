package com.hmilyylimh.cloud.inject.spi;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-08
 */

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.model.ScopeModel;
import org.apache.dubbo.rpc.model.ScopeModelUtil;

public class Geek$Adaptive implements com.hmilyylimh.cloud.inject.spi.Geek {
    public java.lang.String getCourse(org.apache.dubbo.common.URL arg0) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg0;
        String extName = url.getParameter("geek", "dubbo");
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (com.hmilyylimh.cloud.inject.spi.Geek) name from" +
                    " url (" + url.toString() + ") use keys([geek])");
        ScopeModel scopeModel = ScopeModelUtil.getOrDefault(url.getScopeModel(),
                com.hmilyylimh.cloud.inject.spi.Geek.class);
        com.hmilyylimh.cloud.inject.spi.Geek extension =
                (com.hmilyylimh.cloud.inject.spi.Geek) scopeModel.getExtensionLoader(com.hmilyylimh.cloud.inject.spi.Geek.class).getExtension(extName);
        return extension.getCourse(arg0);
    }

    @Override
    public String getCourse2(URL url) {
        return null;
    }
}