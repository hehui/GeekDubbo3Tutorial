package com.hmilyylimh.cloud.wrapper.custom;

public class TimeUtils {

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}