package com.hmilyylimh.cloud.wrapper.spi;

public class CustomSpi implements IDemoSpi {
    @Override
    public int getDefaultPort() {
        return 8888;
    }
}