package com.hmilyylimh.cloud.facade.user.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class SaveUserInfoReq implements Serializable {

    private static final long serialVersionUID = -201498995710974017L;

    private String name;
    private String idNo;
    private String sex;
    private int age;

    public SaveUserInfoReq() {
    }

    public SaveUserInfoReq(String name, String idNo, String sex, int age) {
        this.name = name;
        this.idNo = idNo;
        this.sex = sex;
        this.age = age;
    }
}