package com.hmilyylimh.cloud.deploy.demo;

import com.hmilyylimh.cloud.facade.demo.DemoFacade;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component
@DubboService(timeout = 8888)
public class DemoFacadeImpl implements DemoFacade {

    public DemoFacadeImpl() {
        System.out.println();
    }

    @Override
    public String sayHello(String name) {
        String result = String.format("Hello %s, I'm in 'dubbo-19-dubbo-deploy-provider.", name);
        System.out.println(result);
        return result;
    }
}