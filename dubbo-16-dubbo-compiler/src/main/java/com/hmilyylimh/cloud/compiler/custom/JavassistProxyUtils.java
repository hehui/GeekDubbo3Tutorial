package com.hmilyylimh.cloud.compiler.custom;

import com.hmilyylimh.cloud.compiler.demo.DemoFacadeImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * package com.hmilyylimh.cloud.compiler.custom;
 *
 * import com.hmilyylimh.cloud.facade.demo.DemoFacade;
 * import org.apache.dubbo.common.bytecode.NoSuchMethodException;
 *
 *
 * public class $DemoFacadeCustomInvoker extends CustomInvoker {
 *
 *     @Override
 *     public Object invokeMethod(Object instance, String mtdName, Class<?>[] types, Object[] args) throws NoSuchMethodException {
 *         if ("sayHello".equals(mtdName)) {
 *             return ((DemoFacade) instance).sayHello(String.valueOf(args[0]));
 *         } else if ("say".equals(mtdName)) {
 *             return ((DemoFacade) instance).say();
 *         }
 *
 *         throw new NoSuchMethodException("Method [" + mtdName + "] not found.");
 *     }
 * }
 * </pre>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-03
 */
public class JavassistProxyUtils {

    private static final AtomicInteger INC = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        // 创建源对象（即被代理对象）
        DemoFacadeImpl demoFacade = new DemoFacadeImpl();
        // 生成自定义的代理类
        CustomInvoker invoker = (CustomInvoker) JavassistProxyUtils.newProxyInstance(demoFacade);
        // 调用代理类的方法
        invoker.invokeMethod(demoFacade, "sayHello", new Class[]{String.class}, new Object[]{"Geek"});
    }

    public static Object newProxyInstance(Object sourceTarget) throws Exception{
        // ClassPool：Class对象的容器
        ClassPool pool = ClassPool.getDefault();

        // 通过ClassPool生成一个public类
        Class<?> targetClazz = sourceTarget.getClass().getInterfaces()[0];
        String proxyClassName = "$" + targetClazz.getSimpleName() + "CustomInvoker_" + INC.incrementAndGet();
        CtClass ctClass = pool.makeClass(proxyClassName);
        ctClass.setSuperclass(pool.get("com.hmilyylimh.cloud.compiler.custom.CustomInvoker"));

        // 添加方法  public Object invokeMethod(Object instance, String mtdName, Class<?>[] types, Object[] args) throws NoSuchMethodException { {...}
        CtClass returnType = pool.get("java.lang.Object");
        CtMethod newMethod=new CtMethod(
                returnType,
                "invokeMethod",
                new CtClass[]{ returnType, pool.get("java.lang.String"), pool.get("java.lang.Class[]"), pool.get("java.lang.Object[]") },
                ctClass);
        newMethod.setModifiers(Modifier.PUBLIC);
        newMethod.setBody(buildBody(targetClazz).toString());
        ctClass.addMethod(newMethod);

        // 生成 class 类
        Class<?> clazz = ctClass.toClass();

        // 将 class 文件写到 target 目录下，方便调试查看
        String filePath = JavassistProxyUtils.class.getResource("/").getPath()
                + JavassistProxyUtils.class.getPackage().toString().substring("package ".length()).replaceAll("\\.", "/");
        ctClass.writeFile(filePath);

        // 反射实例化创建对象
        return clazz.newInstance();
    }

    private static StringBuilder buildBody(Class<?> targetClazz) {
        StringBuilder sb = new StringBuilder("{\n");
        for (Method method : targetClazz.getDeclaredMethods()) {
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();

            // if ("sayHello".equals(mtdName)) {
            String ifHead = "if (\"" + methodName + "\".equals($2)) {\n";
            // return ((DemoFacade) instance).sayHello(String.valueOf(args[0]));
            String ifContent = null;
            // 这里有 bug ，姑且就入参就传一个入参对象吧
            if(parameterTypes.length != 0){
                ifContent = "return ((" + targetClazz.getName() + ") $1)." + methodName + "(" + String.class.getName() + ".valueOf($4[0]));\n";
            } else {
                ifContent = "return ((" + targetClazz.getName() + ") $1)." + methodName + "();\n";
            }
            // }
            String ifTail = "}\n";

            sb.append(ifHead).append(ifContent).append(ifTail);
        }
        // throw new NoSuchMethodException("Method [" + mtdName + "] not found.");
        String invokeMethodTailContent = "throw new " + org.apache.dubbo.common.bytecode.NoSuchMethodException.class.getName() + "(\"Method [\" + $2 + \"] not found.\");\n}\n";
        sb.append(invokeMethodTailContent);
        return sb;
    }
}
