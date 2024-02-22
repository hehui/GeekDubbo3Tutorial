//package com.hmilyylimh.cloud.validation.impl;
//
//import com.hmilyylimh.cloud.facade.valid.ValidationFacade;
//import com.hmilyylimh.cloud.facade.valid.bean.ValidateUserInfo;
//import org.apache.dubbo.common.utils.StringUtils;
//import org.apache.dubbo.config.annotation.DubboService;
//import org.springframework.stereotype.Component;
//
///**
// * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
// *
// * @author hmilyylimh
// * ^_^
// * @version 0.0.1
// * ^_^
// * @date 2022-11-13
// */
//@DubboService(validation = "true")
//@Component
//public class ValidationFacadeImpl2 implements ValidationFacade {
//
//    @Override
//    public String validateUser(ValidateUserInfo userInfo) {
//        // 校验 id 属性必填
//        if (StringUtils.isBlank(userInfo.getId())) {
//            throw new RuntimeException("[stub] id 不能为空");
//        }
//        // 校验 name 属性的长度必须在 5~10 之间
//        String name = userInfo.getName();
//        if (StringUtils.isNotBlank(name) && (name.length() < 5 || name.length() > 10)) {
//            throw new RuntimeException("[stub] name 必须在 5~10 个长度之间");
//        }
//
//        // 这里就象征性的模拟下业务逻辑
//        String retMsg = "Ret: "
//                + userInfo.getId()
//                + "," + userInfo.getName()
//                + "," + userInfo.getSex();
//        System.out.println(retMsg);
//        return retMsg;
//    }
//}