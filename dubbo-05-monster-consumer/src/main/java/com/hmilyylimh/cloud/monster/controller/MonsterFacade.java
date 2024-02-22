package com.hmilyylimh.cloud.monster.controller;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-10-31
 */
public interface MonsterFacade {

    AbstractResponse heretical(HereticalReq req);

    //////////////////////////////////////////////////////////////////////////////////
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已
    // 下面的空实现仅示意代码思路而已

    class HereticalReq {
        private String javaCode;
        private Object reqParamsMap;

        public String getJavaCode() {
            return javaCode;
        }

        public void setJavaCode(String javaCode) {
            this.javaCode = javaCode;
        }

        public Object getReqParamsMap() {
            return reqParamsMap;
        }

        public void setReqParamsMap(Object reqParamsMap) {
            this.reqParamsMap = reqParamsMap;
        }
    }

    class AbstractResponse {
        public AbstractResponse(Object resp) {

        }
    }
}
