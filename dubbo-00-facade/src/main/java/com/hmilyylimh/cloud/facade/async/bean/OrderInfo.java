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
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 5463007349232695949L;

    private String id;
    private String productName;
    private BigDecimal productAmt;

    public OrderInfo() {
    }

    public OrderInfo(String id, String productName, BigDecimal productAmt) {
        this.id = id;
        this.productName = productName;
        this.productAmt = productAmt;
    }
}
