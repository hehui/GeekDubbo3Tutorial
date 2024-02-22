package com.hmilyylimh.cloud.loadcfg.crypto;

import com.hmilyylimh.cloud.facade.crypto.CryptoFacade;
import com.hmilyylimh.cloud.loadcfg.util.TimeUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-24
 */
@Component
@DubboService
public class CryptoFacadeImpl implements CryptoFacade {

    @Override
    public String decrypt(String encryptContent) {
        // 睡眠 5000 毫秒，模拟解密耗时情况
        TimeUtils.sleep(5 * 1000);
        // 象征性的组装一下返回的明文结果
        String result = String.format("密文为: %s, 解密后的明文为: %s", encryptContent, "PLAIN-" + encryptContent);
        // 顺便在提供方打印一下返回的结果
        System.out.println(result);
        return result;
    }
}
