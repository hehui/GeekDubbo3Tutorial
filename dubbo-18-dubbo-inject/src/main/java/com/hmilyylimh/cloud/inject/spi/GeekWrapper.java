package com.hmilyylimh.cloud.inject.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Wrapper;

@Wrapper(order = 10, mismatches = {"springcloud"})
public class GeekWrapper implements Geek {

    private Geek geek;
    private Animal monkey;

    public void setMonkey(Animal monkey){
        this.monkey = monkey;
    }

    public GeekWrapper(Geek geek) {
        this.geek = geek;
    }

    @Override
    public String getCourse(URL url) {
        return "【课程GeekWrapper前...】" + geek.getCourse(url) + "【课程GeekWrapper后...】||【"+monkey.eat(url)+"】";
    }

    @Override
    public String getCourse2(URL url) {
        return "【课程2前...】" + geek.getCourse2(url) + "【课程2后...】";
    }
}