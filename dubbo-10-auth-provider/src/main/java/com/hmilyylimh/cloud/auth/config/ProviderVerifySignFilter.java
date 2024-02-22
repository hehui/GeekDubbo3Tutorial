package com.hmilyylimh.cloud.auth.config;

import com.hmilyylimh.cloud.auth.util.SignUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;

import java.util.Map;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-22
 */
@Activate(group = PROVIDER)
public class ProviderVerifySignFilter implements Filter {

    /** <h2>SING 字段名</h2> **/
    public static final String SING = "SING";

    /** <h2>方法级别层面获取配置的 auth.ras.enable 参数名</h2> **/
    public static final String KEY_AUTH_RSA_ENABLE = "auth.rsa.enable";

    /** <h2>方法级别层面获取配置的 auth.rsa.public.secret 参数名</h2> **/
    public static final String KEY_AUTH_RSA_PUBLIC_SECRET = "auth.rsa.public.secret";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 从方法层面获取 auth.ras.enable 参数值
        String authRsaEnable = invoker.getUrl().getMethodParameter
                (invocation.getMethodName(), KEY_AUTH_RSA_ENABLE);
        // 如果不需要验签的话，则继续后面过滤器的调用
        if (!Boolean.TRUE.toString().equals(authRsaEnable)) {
            return invoker.invoke(invocation);
        }

        // 能来到这里，说明需要进行验签
        Map<String, Object> attachments = invocation.getObjectAttachments();
        String recvSign = attachments != null ? (String) attachments.get(SING) : null;
        // 既然需要认证，如果收到的加签值为空的话，则直接抛异常
        if (StringUtils.isBlank(recvSign)) {
            throw new RuntimeException(
                    "Recv sign is null or empty, path: " +
                     String.join(".", invoker.getInterface().getName(), invocation.getMethodName()));
        }

        // 从方法层面获取 auth.rsa.public.secret 参数值
        String rsaPublicSecretOpsKey = invoker.getUrl().getMethodParameter
                (invocation.getMethodName(), KEY_AUTH_RSA_PUBLIC_SECRET);
        // 从 OPS 配置中心里面获取到 rsaPublicSecretOpsKey 对应的密钥值
        String publicKey = OpsUtils.get(rsaPublicSecretOpsKey);
        // 加签处理
        boolean passed = SignUtils.verifySign(invocation.getArguments(), publicKey, recvSign);
        // sign 不为空的话则设置到请求对象中
        if (!passed) {
            throw new RuntimeException(
                    "Recv sign is invalid, path: " +
                     String.join(".", invoker.getInterface().getName(), invocation.getMethodName()));
        }

        // 继续后面过滤器的调用
        return invoker.invoke(invocation);
    }

    static class OpsUtils {

        public static String get(String opsKey){
            // 这里为了演示，就直接返回了一个固定的值
            if("queryPayAccoun_privateSecret".equals(opsKey)){
                return _PRIVATE_KEY;
            } else if("queryPayAccoun_publicSecret".equals(opsKey)){
                return _PUBLIC_KEY;
            }
            return null;
        }

    }

    private static final String _PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCV0jSn5mi5nFfGcKt3dut7Q9Mlr2k0LpjCUCbyNAWKzJ2JzTnHAIl6Rcip1b9hqeEqPX7PeW6tnyyt+EFEjm+C5AlZri/NTLKr8X0vB/enjGwQs7jmYkUa6V7bRvgMOAAtxRCFtkzWDMwNiHW9CasYQ6akeWhqcQWA8wB0IksPBPqxeMPsm2ZP+WJphBLY5Yk6svHCezLjlay9JrkJR98fbI33vUFvqofq1DFD0d0WtwWD4qhm/aaEo73tBQ9JMV6WFtEHocJ8VSApIXh8wy3lixcci6JQzSwHVwgaGFN1sfmMGF5nld+siiGL59FdnrkRO9bpkCtoxdMNi/iXZEHnAgMBAAECggEAPBYuIBh8b9SQL3oIisUa9Djjef27x73YbjYPKJxMjLo7hITWY0WH3Y4XSGX9d4HWWEaJkVQ2W1O3a55hLsmhV3F1fo4phcD495TGjBI59OyQerJZuaw34u8tp+vyl5PuHm0mjznp3v8K53KPJd22zOh08QnwIqBpgKn0yJ7oU4EgpTiI70wgnwE+gNzcABSd3sSVGJLhZYKZQGlQcqZG6mrz2CimCWaycS1Gnvddzv2D8CvjwyPT29yerrwBVaDXiaZIdZXLPKOmgoDAA3M6PeuhmwYQmb1wpdHOTZVcgNRfgnGeQWSfuFn7/Pfmnfp/jCn4CiBjRwlzoMbbyNGCoQKBgQDlm4adiWTYkGEJC1ABlfPrdPsO7rI09EZ2DlZkbuICEEAsXkf/+7ZWHiXJAqxzW0bmJt8TC14wVkniLg3CpFr3/yxnRMpI2OwTUQpHXDc4sUqlZeQYnTukebikOt6fChx9+9UkrdbzGhD9WBEPmHP5FUFxx5H4FBqETUjltq9+nQKBgQCnCt66MyqOG4U0m+u2SG5YHplX7U20zgnW7l63rSZOWG/qCvDj7mUjbMRN4HgnjoZW8QVqR1RHyKnHEKMRuM1jzJViSEEpv6CnLc7IDXbTVmaM/3jeXIOZDZNJHPCY5NDkVgIATz4dVuNhJQkYl3UHrpc0Cx6tvudHU7ye3F55UwKBgQDJSCoeeI+7efZW41/jw/zs8MQWNxJqcapDXBZIPRxMCsr3Gj+sHJGftRYSvRFInz/sCy+Llm+fmEV0zkKp20MUHNJkRV2/tspdtLF6FVEjleiGTmhDW7MpYLuI3ULD0S1hFB+p4+uHedZjAY0TSuZ/+S5B/F3uSFFmognBtwDp0QKBgQCVp4RyN4MJvBg0UPj136CUbB2gC+fRc8KXAAp17rfh+xOREGpPGvcbrMeWqeOcNPGglODwHaWhhmKtCPe/2dyRa+nuGqPe3mL1RHH9AysRHlhUPFGyjhoMln/9QeNAZ8fKuk+irVwN9xlRoFes44yi7EVlCA1dRip42cmkXfR6JwKBgQDJKvR7nKGlSltCsi9seIsd9q9ytNTrSnI+Eh91xlBgChaxLn53xZLfbUsQtfcc+Z/vmu7hkcfSLtOB7a7AwNWVz3oy8fSCZAbQm9EPkXxREoJRjWNpyHJm5WXYQ/rs9YgFXMQYHkrTN6ZLiwXiEAAU4yCwx46u812IpTV6sKsDkg==";
    private static final String _PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAldI0p+ZouZxXxnCrd3bre0PTJa9pNC6YwlAm8jQFisydic05xwCJekXIqdW/YanhKj1+z3lurZ8srfhBRI5vguQJWa4vzUyyq/F9Lwf3p4xsELO45mJFGule20b4DDgALcUQhbZM1gzMDYh1vQmrGEOmpHloanEFgPMAdCJLDwT6sXjD7JtmT/liaYQS2OWJOrLxwnsy45WsvSa5CUffH2yN971Bb6qH6tQxQ9HdFrcFg+KoZv2mhKO97QUPSTFelhbRB6HCfFUgKSF4fMMt5YsXHIuiUM0sB1cIGhhTdbH5jBheZ5XfrIohi+fRXZ65ETvW6ZAraMXTDYv4l2RB5wIDAQAB";

}