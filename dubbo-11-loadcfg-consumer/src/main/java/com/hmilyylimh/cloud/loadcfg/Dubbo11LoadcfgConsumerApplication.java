package com.hmilyylimh.cloud.loadcfg;

import com.hmilyylimh.cloud.facade.crypto.CryptoFacade;
import com.hmilyylimh.cloud.loadcfg.util.TimeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

// -Ddubbo.properties.file=C:\Users\ThinP\Desktop\dubbo.properties

@ImportResource("classpath:dubbo-11-loadcfg-consumer.xml")
@SpringBootApplication
public class Dubbo11LoadcfgConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Dubbo11LoadcfgConsumerApplication.class, args);
        System.out.println("【【【【【【 Dubbo11LoadcfgConsumerApplication 】】】】】】已启动.");

        CryptoFacade cryptoFacade = ctx.getBean(CryptoFacade.class);
        System.out.println(cryptoFacade.decrypt("Geek"));
    }
}
