package com.hmilyylimh.cloud.filter.config;

import com.alibaba.fastjson.JSON;
import com.hmilyylimh.cloud.filter.util.AesUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.support.RpcUtils;

import java.util.List;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * <h1>解密过滤器。</h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-22
 */
@Activate(group = PROVIDER)
public class DecryptProviderFilter implements Filter {

    /** <h2>配置中心 AES 密钥的配置名称，通过该名称就能从配置中心拿到对应的密钥值</h2> **/
    public static final String CONFIG_CENTER_KEY_AES_SECRET = "CONFIG_CENTER_KEY_AES_SECRET";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 从 OPS 配置中心里面获取到 aesSecretOpsKey 对应的密钥值
        String privateKey = OpsUtils.getAesSecret(CONFIG_CENTER_KEY_AES_SECRET);

        // 获取此次请求的类名、方法名，并且构建出一个唯一的 KEY
        String serviceName = invocation.getServiceModel().getServiceKey();
        String methodName = RpcUtils.getMethodName(invocation);
        String uniqueKey = String.join("_", serviceName, methodName);

        // 通过唯一 KEY 从配置中心查询出来的值为空，则说明该方法不需要解密
        // 那么就当作什么事也没发生，继续走后续调用逻辑
        String configVal = OpsUtils.get(uniqueKey);
        if (StringUtils.isBlank(configVal)) {
            return invoker.invoke(invocation);
        }

        // 能来到这里说明通过唯一 KEY 从配置中心找到了配置，那么就直接将找到的配置值反序列化为对象
        DecryptConfig decryptConfig = JSON.parseObject(configVal, DecryptConfig.class);
        // 循环解析配置中的所有字段列表，然后挨个解密并回填明文值
        for (String fieldPath : decryptConfig.getFieldPath()) {
            // 通过查找节点工具类，通过 fieldPath 字段路径从 invocation 中找出对应的字段值
            String encryptContent = PathNodeUtils.failSafeGetValue(invocation, fieldPath);
            // 找出来的字段值为空的话，则不做任何处理，继续处理下一个字段
            if (StringUtils.isBlank(encryptContent)) {
                continue;
            }

            // 解密成为明文后，则继续将明文替换掉之前的密文
            String plainContent = AesUtils.decrypt(encryptContent, privateKey);
            PathNodeUtils.failSafeSetValue(invocation, fieldPath, plainContent);
        }

        // 能来到这里，说明解密完成，invocation 中已经是明文数据了，然后继续走后续调用逻辑
        return invoker.invoke(invocation);
    }

    /**
     * <h1>解密配置。</h1>
     */
    @Setter
    @Getter
    public static class DecryptConfig {
        List<String> fieldPath;
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

    private static class OpsUtils {
        public static String get(String uniqueKey) {
            return null;
        }

        public static String getAesSecret(String configCenterKeyAesSecret) {
            return null;
        }
    }

    private static class PathNodeUtils {
        public static String failSafeGetValue(Invocation invocation, String fieldPath) {
            return null;
        }

        public static void failSafeSetValue(Invocation invocation, String fieldPath, String plainContent) {
        }
    }
}