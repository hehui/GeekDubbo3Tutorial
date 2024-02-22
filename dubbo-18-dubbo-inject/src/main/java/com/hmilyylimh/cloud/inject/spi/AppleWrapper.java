package com.hmilyylimh.cloud.inject.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Wrapper;


@Wrapper(order = 1)
public class AppleWrapper implements Geek {

    private Geek geek;

//    public AppleWrapper(Geek geek) {
//        this.geek = geek;
//    }

//    private Animal animal;
//    public AppleWrapper(Geek geek, Animal animal) {
//        this.geek = geek;
//        this.animal = animal;
//    }

//    public AppleWrapper(Animal animal) {
//        this.animal = animal;
//    }

//    private Geek springCloud;
//    public void setSpringcloud(Geek geek){
//        this.springCloud = geek;
//    }

    @Override
    public String getCourse(URL url) {
        // return springCloud.getCourse(url) + "【课程AppleWrapper前...】" + geek.getCourse(url) + "【课程AppleWrapper后...】";
        return "【课程AppleWrapper前...】" + geek.getCourse(url) + "【课程AppleWrapper后...】";
    }

    @Override
    public String getCourse2(URL url) {
        return "【课程2前...】" + geek.getCourse2(url) + "【课程2后...】";
    }
}