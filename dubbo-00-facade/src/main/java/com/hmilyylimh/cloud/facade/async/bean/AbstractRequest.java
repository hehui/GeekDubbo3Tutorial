package com.hmilyylimh.cloud.facade.async.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-25
 */
@Setter
@Getter
@ToString
public class AbstractRequest implements Serializable {

    /** <h2>请求流水号，打印日志使用</h2> **/
    private String reqNo;
}
