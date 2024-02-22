package com.hmilyylimh.cloud.compiler.asm;

/**
 * <h1>Asm测试类。</h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2021-10-24
 */
public class AsmTest {

    public static void main(String[] args) throws Exception {
        UserBean userBean = new UserBean("Geek");

        // 默认从 mybatis 的拦截器里面拿到了准备更新 db 的数据对象，然后创建代理对象
        Handler handler = (Handler) AsmProxyUtils.newProxyInstance(userBean.getClass());

        // 关键的一步，在 mybatis 中模拟将入参对象进行加密操作
        handler.addBefore(userBean);
        // 这里为了观察效果，先打印一下 userBean 的内容看看
        System.out.println("打印一下加密内容: " + userBean);

        // 接下来，假设有执行 db 的操作，那就直接将密文入库了

        // db 操作完成之后，还得将 userBean 的密文变成明文，这里应该还有 addAfter 解密操作
    }
}