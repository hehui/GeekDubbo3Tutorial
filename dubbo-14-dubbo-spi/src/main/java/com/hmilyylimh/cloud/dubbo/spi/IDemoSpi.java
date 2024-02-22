package com.hmilyylimh.cloud.dubbo.spi;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface IDemoSpi {
    int getDefaultPort();
}