package com.hmilyylimh.cloud.inject.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("monkey")
public interface Animal {

    @Adaptive
    String eat(URL url);
}