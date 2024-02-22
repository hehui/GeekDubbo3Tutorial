package com.hmilyylimh.cloud.compiler.asm;

/**
 * <h1>样例接口实例</h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-12-04
 *
 */
public class UserBeanHandler implements Handler<UserBean> {

    @Override
    public void addBefore(UserBean u) {
        if (u.getName() != null && u.getName().length() > 0) {
            // 我这里仅仅只是告诉大家我针对了 name 的这个字段做了处理，
            // 以后大家应用到实际项目中的话，可以在这里将我们的 name 字段进行加密处理
            u.setName("#BEFORE#" + u.getName());
        }
    }
}