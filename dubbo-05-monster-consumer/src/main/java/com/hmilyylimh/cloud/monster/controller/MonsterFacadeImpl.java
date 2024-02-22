package com.hmilyylimh.cloud.monster.controller;

import groovy.lang.GroovyClassLoader;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-10-31
 */
public class MonsterFacadeImpl implements MonsterFacade {

    private GroovyClassLoader groovyClassLoader;

    @Override
    public AbstractResponse heretical(HereticalReq req){
        // 编译 Java 代码，然后变成 JVM 可识别的 Class 对象信息
        Class<?> javaClass = compile(req.getJavaCode());
        // 为 Class 对象信息，自定义一个名称，将来创建 Spring 单例对象要用到
        String beanName = "Custom" + javaClass.getSimpleName();

        // 通过 Spring 来创建单例对象
        generateSpringBean(beanName, javaClass);

        // 获取 beanName 对应的单例对象
        MonsterInvokeRunnable runnable = (MonsterInvokeRunnable) SpringContextUtils.getBean(beanName);

        // 执行单例对象的方法即可
        Object resp = runnable.run(req.getReqParamsMap());

        // 返回结果
        return new AbstractResponse(resp);
    }

    private Class<?> compile(String javaCode){
        return groovyClassLoader.parseClass(javaCode);
    }

    private void generateSpringBean(String beanName, Class<?> javaClass){
        // 构建 Bean 定义对象
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(javaClass);
        AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        // 将 bean 移交给 Spring 去管理
        ConfigurableApplicationContext appCtx =
                (ConfigurableApplicationContext)SpringContextUtils.getContext();
        appCtx.getAutowireCapableBeanFactory()
                .applyBeanPostProcessorsAfterInitialization(rawBeanDefinition, beanName);
        ((BeanDefinitionRegistry)appCtx.getBeanFactory()).registerBeanDefinition(beanName, rawBeanDefinition);
    }

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

    private static class SpringContextUtils {
        public static Object getBean(String beanName) {
            return null;
        }

        public static Object getContext() {
            return null;
        }
    }

    private class MonsterAction {
    }

    private class MonsterInvokeRunnable {
        public Object run(Object reqParamsMap) {
            return null;
        }
    }
}
