package com.hmilyylimh.cloud.rpcctx.demo;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component
@DubboService
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public String sayHello(String name) {
        String result = String.format("Hello %s, I'm in 'dubbo-03-rpcctx-transfer.", name);
        System.out.println(result);
        return result;
    }
}