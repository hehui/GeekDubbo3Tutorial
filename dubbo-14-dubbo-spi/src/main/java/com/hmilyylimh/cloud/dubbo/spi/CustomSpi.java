package com.hmilyylimh.cloud.dubbo.spi;

public class CustomSpi implements IDemoSpi {
    @Override
    public int getDefaultPort() {
        return 8888;
    }
}