package com.hmilyylimh.cloud.facade.async;

import com.hmilyylimh.cloud.facade.async.bean.OrderInfo;

import java.util.concurrent.CompletableFuture;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-25
 */
public interface AsyncOrderFacade {

    public OrderInfo queryOrderById(String id);

    public CompletableFuture<OrderInfo> queryDetailById(String id);
}