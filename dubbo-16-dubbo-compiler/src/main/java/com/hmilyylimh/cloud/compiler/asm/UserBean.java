package com.hmilyylimh.cloud.compiler.asm;

/**
 * <h1>用户对象，目前仅仅只是用于demo编写测试，所以这里就暂时只写一个字段了。</h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2021-10-24
 */
public class UserBean {


    private String name;

    public UserBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() { return "UserBean{name='" + name + '\'' + '}'; }
}