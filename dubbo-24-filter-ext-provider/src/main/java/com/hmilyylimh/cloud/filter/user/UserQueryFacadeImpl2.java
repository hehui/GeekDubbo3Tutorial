package com.hmilyylimh.cloud.filter.user;

import com.hmilyylimh.cloud.facade.user.UserQueryFacade;
import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoReq;
import com.hmilyylimh.cloud.facade.user.bean.SaveUserInfoResp;
import com.hmilyylimh.cloud.facade.user.bean.UserInfo;
import com.hmilyylimh.cloud.filter.util.AesUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-18
 */
//@DubboService
//@Component
public class UserQueryFacadeImpl2 implements UserQueryFacade {

    /** <h2>配置中心 AES 密钥的配置名称，通过该名称就能从配置中心拿到对应的密钥值</h2> **/
    public static final String CONFIG_CENTER_KEY_AES_SECRET = "CONFIG_CENTER_KEY_AES_SECRET";

    @Override
    public SaveUserInfoResp saveUserInfo(SaveUserInfoReq req) {
        // 从 OPS 配置中心里面获取到 aesSecretOpsKey 对应的密钥值
        String privateKey = OpsUtils.getAesSecret(CONFIG_CENTER_KEY_AES_SECRET);

        // 将入参的 name、idNo 字段进行解密
        String plainName = AesUtils.decrypt(req.getName(), privateKey);
        String plainIdNo = AesUtils.decrypt(req.getIdNo(), privateKey);

        // 然后将得到的明文 plainName、plainIdNo 再放回到入参 req 中去
        req.setName(plainName);
        req.setIdNo(plainIdNo);

        // 保存用户信息的核心逻辑
        doSaveUserInfo(req);

        // 返回成功响应
        return buildSuccResp();
    }

    @Override
    public String queryUser(String name) {
        return null;
    }

    @Override
    public UserInfo queryUserInfo(UserInfo userInfo) {
        return null;
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

    private SaveUserInfoResp buildSuccResp() {
        return new SaveUserInfoResp();
    }

    private void doSaveUserInfo(SaveUserInfoReq req) {

    }

    private static class OpsUtils {
        public static String getAesSecret(String opsKeyAesSecret) {
            return null;
        }
    }
}
