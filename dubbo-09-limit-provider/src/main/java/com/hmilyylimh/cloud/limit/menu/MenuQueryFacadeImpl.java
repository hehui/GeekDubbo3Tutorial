package com.hmilyylimh.cloud.limit.menu;

import com.hmilyylimh.cloud.facade.menu.MenuQueryFacade;
import com.hmilyylimh.cloud.limit.util.TimeUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Component;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-18
 */
@DubboService(methods = {@Method(
        name = "queryAuthorizedMenuList",
        parameters = {
                "qps.enable", "true",
                "qps.value", "3"
        })}
)
@Component
public class MenuQueryFacadeImpl implements MenuQueryFacade {

    @Override
    public String queryAuthorizedMenuList(String userId) {
        // 睡眠 1 秒，模拟一下查询数据库需要耗费时间
        TimeUtils.sleep(1000);
        String result = String.format(TimeUtils.now() + ": Hello %s, 已查询该用户已授权的【菜单列表信息】", userId);
        System.out.println(result);
        return result;
    }
}
