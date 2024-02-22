package com.hmilyylimh.cloud.wrapper.spi;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface IDemoSpi {
    int getDefaultPort();
}