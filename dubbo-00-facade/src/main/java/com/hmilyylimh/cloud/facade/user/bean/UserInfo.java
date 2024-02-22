package com.hmilyylimh.cloud.facade.user.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1558193327511325424L;

    private String id;
    private String name;
    private String sex;

    public UserInfo() {
    }

    public UserInfo(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}