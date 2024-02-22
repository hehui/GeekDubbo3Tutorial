package com.hmilyylimh.cloud.monster.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

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
public class MonsterController {

    // 响应码为成功时的值
    public static final String SUCC = "000000";

    @Setter
    @Getter
    public static class RepairRequest {
        /** <h2>接口类名，例：com.xyz.MonsterFacade</h2> **/
        private String className;
        /** <h2>接口方法名，例：heretical</h2> **/
        private String mtdName;
        /** <h2>接口方法参数类名，例：com.xyz.bean.HereticalReq</h2> **/
        private String parameterTypeName;
        /** <h2>指定的URL节点，例：dubbo://ip:port</h2> **/
        private String url;
        /** <h2>可以是调用具体接口的请求参数，也可以是修复问题的Java代码</h2> **/
        private String paramsMap;
    }

    // 定义URL地址
    @PostMapping("/gateway/repair/request")
    public String repairRequest(@RequestBody RepairRequest repairRequest){
        // 将入参的req转为下游方法的入参对象，并发起远程调用
        return commonInvoke(repairRequest);
    }

    private String commonInvoke(RepairRequest repairRequest) {
        // 然后试图通过类信息对象想办法获取到该类对应的实例对象
        ReferenceConfig<GenericService> referenceConfig =
                createReferenceConfig(repairRequest.getClassName(), repairRequest.getUrl());

        // 远程调用
        GenericService genericService = referenceConfig.get();
        Object resp = genericService.$invoke(
                repairRequest.getMtdName(),
                new String[]{repairRequest.getParameterTypeName()},
                new Object[]{JSON.parseObject(repairRequest.getParamsMap(), Map.class)});

        // 判断响应对象的响应码，不是成功的话，则组装失败响应
        if(!SUCC.equals(OgnlUtils.getValue(resp, "respCode"))){
            return RespUtils.fail(resp);
        }

        // 如果响应码为成功的话，则组装成功响应
        return RespUtils.ok(resp);
    }

    private static ReferenceConfig<GenericService> createReferenceConfig(String className, String url) {
        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();

        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboBootstrap.getApplicationModel().getApplicationName());

        // 设置注册中心的地址
        String address = dubboBootstrap.getConfigManager().getRegistries().iterator().next().getAddress();
        RegistryConfig registryConfig = new RegistryConfig(address);

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(className);

        // 设置泛化调用形式
        referenceConfig.setGeneric("true");
        // 设置默认超时时间5秒
        referenceConfig.setTimeout(5 * 1000);
        // 设置点对点连接的地址
        referenceConfig.setUrl(url);
        return referenceConfig;
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

    private static class RespUtils {
        public static String ok(Object resp) {
            return null;
        }

        public static String fail(Object resp) {
            return null;
        }
    }

    private static class OgnlUtils {
        public static String getValue(Object resp, String respCode) {
            return null;
        }
    }
}
