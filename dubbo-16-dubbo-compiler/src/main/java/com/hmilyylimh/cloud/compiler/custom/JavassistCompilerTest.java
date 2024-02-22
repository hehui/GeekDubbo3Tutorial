package com.hmilyylimh.cloud.compiler.custom;

import org.apache.dubbo.common.compiler.support.JavassistCompiler;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-04
 */
public class JavassistCompilerTest {

    public static void main(String[] args) throws Exception {
        // 直接将 $DemoFacadeCustomInvoker 内容拷贝过来试一试
        String sourceCode = "public class DemoFacadeImpl implements DemoFacade {     " +
                "public String sayHello(String name) {" +
                "String result = \"Hello \" + name + \", I'm in 'javassistCompiler.\";" +
                "System.out.println(result);     " +
                "return result ;" +
                "}}";

        // 创建 Javassist 编译器
        JavassistCompiler compiler = new JavassistCompiler();
        // 编译源代码
        Class<?> clz = compiler.compile(sourceCode, JavassistCompilerTest.class.getClassLoader());
        // 将类信息利用反射进行实例化
        Object instance = clz.newInstance();
        // 反射调用方法
        instance.getClass().getMethod("sayHello", new Class[]{String.class}).invoke(instance, "Geek");
    }
}
