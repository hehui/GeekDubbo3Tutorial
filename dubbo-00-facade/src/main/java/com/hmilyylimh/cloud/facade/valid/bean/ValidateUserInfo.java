package com.hmilyylimh.cloud.facade.valid.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * <h1>用户校验信息。</h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2021-11-01
 */
public class ValidateUserInfo implements Serializable {

    private static final long serialVersionUID = 1558193327511325424L;

    public ValidateUserInfo() {
    }

    public ValidateUserInfo(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    @NotBlank(message = "id 不能为空")
    private String id;
    @Length(min = 5, max = 10, message = "name 必须在 5~10 个长度之间")
    private String name;
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}