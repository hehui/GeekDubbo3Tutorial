package com.hmilyylimh.cloud.facade.async.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <h1>抽象响应对象。</h1>
 *
 * @author hmilyylimh
 *         ^_^
 * @version 0.0.1
 *         ^_^
 * @date 2022-12-18
 *
 */
@Setter
@Getter
@ToString
public class AbstractResponse implements Serializable {

    /** <h2>响应码</h2> **/
    private String respCode;

    /** <h2>响应描述信息</h2> **/
    private String respMsg;

    public AbstractResponse() {
        this.respCode = "000000";
        this.respMsg = "成功";
    }

    public AbstractResponse(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }
}
