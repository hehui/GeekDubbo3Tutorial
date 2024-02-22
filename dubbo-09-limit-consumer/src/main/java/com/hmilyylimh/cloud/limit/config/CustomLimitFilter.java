package com.hmilyylimh.cloud.limit.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-11-20
 */
@Activate(group = {PROVIDER, CONSUMER})
public class CustomLimitFilter implements Filter {

    /** <h2>存储计数资源的Map数据结构，预分配容量64，避免无畏的扩容消耗</h2> **/
    private static final ConcurrentMap<String, AtomicInteger> COUNT_MAP = new ConcurrentHashMap<>(64);

    /** <h2>标识启动QPS限流检测，{@code true}：标识开启限流检测，{@code false 或 null}：标识不开启限流检测</h2> **/
    public static final String KEY_QPS_ENABLE = "qps.enable";

    /** <h2>处理限流的工具，枚举值有：jlimit-JMV限流；rlimit-Redis限流。</h2> **/
    public static final String KEY_QPS_TYPE = "qps.type";

    /** <h2>处理限流的工具，jlimit-JMV限流</h2> **/
    public static final String VALUE_QPS_TYPE_OF_JLIMIT = "jlimit";

    /** <h2>处理限流的工具，rlimit-Redis限流。</h2> **/
    public static final String VALUE_QPS_TYPE_OF_RLIMIT = "rlimit";

    /** <h2>每个方法开启的限流检测值</h2> **/
    public static final String KEY_QPS_VALUE = "qps.value";

    /** <h2>默认的限流检测值</h2> **/
    public static final long DEFAULT_QPS_VALUE = 30;

    /** <h2>策略分发，通过不同的 qps.type 值来选择不同的限流工具进行获取计数资源处理</h2> **/
    private static final Map<String, BiFunction<URL, Invocation, Boolean>> QPS_TYPE_ACQUIRE_MAP = new HashMap<>(4);

    /** <h2>策略分发，通过不同的 qps.type 值来选择不同的限流工具进行释放计数资源处理</h2> **/
    private static final Map<String, BiConsumer<URL, Invocation>> QPS_TYPE_RELEASE_MAP = new HashMap<>(4);

    /** <h2>这里得想办法采取扫描机制简单支持 @Autowired、@Resource 两个注解即可</h2> **/
//    @Autowired
//    private RedisTemplate redisTemplate;

    /** <h2>利用默认的构造方法在创建的时候，顺便把两个策略Map初始化一下</h2> **/
    public CustomLimitFilter() {
        init();
    }

    private void init() {
        QPS_TYPE_ACQUIRE_MAP.put(VALUE_QPS_TYPE_OF_JLIMIT, (url, invocation) -> tryAcquireOfJvmLimit(url, invocation));
        QPS_TYPE_ACQUIRE_MAP.put(VALUE_QPS_TYPE_OF_RLIMIT, (url, invocation) -> tryAcquireOfRedisLimit(url, invocation));

        QPS_TYPE_RELEASE_MAP.put(VALUE_QPS_TYPE_OF_JLIMIT, (url, invocation) -> releaseOfJvmLimit(url, invocation));
        QPS_TYPE_RELEASE_MAP.put(VALUE_QPS_TYPE_OF_RLIMIT, (url, invocation) -> releaseOfRedisLimit(url, invocation));
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 获取限流资源的结果，acquired 有三种值：
        // true：获取到计数资源；false：计数已满，无法再获取计数资源；null：不需要限流
        Boolean acquired = null;
        try {
            // 尝试是否能获取到限流计数资源
            acquired = tryAcquire(invoker.getUrl(), invocation);
            // 若获取不到计数资源的话，则直接抛出异常即可，告知调用方限流了
            if (acquired != null && !acquired) {
                throw new RuntimeException(
                    "Failed to acquire service " +
                     String.join(".", invoker.getInterface().getName(), invocation.getMethodName()) +
                     " because of overload.");
            }

            // 能来到这里，要么是不需要限流，要么就是获取到了计数资源，那就直接继续下一步调用即可
            return invoker.invoke(invocation);
        } finally {
            // 调用不管是成功还是失败，都是需要进行计数资源释放的
            release(acquired, invoker.getUrl(), invocation);
        }
    }

    // 尝试是否能获取到限流计数资源
    private Boolean tryAcquire(URL url, Invocation invocation) {
        // 从方法层面获取 qps.enable 参数值，如果为 true 则表示开启限流控制，否则不需要限流
        String qpsEnableFlag = url.getMethodParameter(invocation.getMethodName(), KEY_QPS_ENABLE);
        if (!Boolean.TRUE.toString().equals(qpsEnableFlag)) {
            return null;
        }

        // 从方法层面获取 qps.type 参数值，默认采用 JVM 内存来处理限流，若设置的类型从 Map 中找不到则当作不需要限流处理
        String qpsTypeVal = url.getMethodParameter
                (invocation.getMethodName(), KEY_QPS_TYPE, VALUE_QPS_TYPE_OF_JLIMIT);
        BiFunction<URL, Invocation, Boolean> func = QPS_TYPE_ACQUIRE_MAP.get(qpsTypeVal);
        if (func == null) {
            return null;
        }

        // 根据 qps.type 找到对应的工具进行策略分发按照不同的工具进行限流处理
        return func.apply(url, invocation);
    }

    // 进行计数资源释放
    private void release(Boolean acquired, URL url, Invocation invocation) {
        // 若不需要限流，或者没有获取到计数资源，都不需要进行计数资源释放
        if(acquired == null || !acquired){
            return;
        }

        // 从方法层面获取 qps.type 参数值，默认采用 JVM 内存来处理限流，若设置的类型从 Map 中找不到则当作不需要限流处理
        String qpsTypeVal = url.getMethodParameter
                (invocation.getMethodName(), KEY_QPS_TYPE, VALUE_QPS_TYPE_OF_JLIMIT);
        BiConsumer<URL, Invocation> func = QPS_TYPE_RELEASE_MAP.get(qpsTypeVal);
        if (func == null) {
            return;
        }

        // 根据 qps.type 找到对应的工具进行策略分发按照不同的工具进行释放计数资源处理
        func.accept(url, invocation);
    }

    // 通过JVM内存的处理方式，来尝试是否能获取到限流计数资源
    private Boolean tryAcquireOfJvmLimit(URL url, Invocation invocation) {
        // 从方法层面获取 qps.value 限流的标准容量，如果没配置则默认为 30
        long qpsValue = url.getMethodParameter
                (invocation.getMethodName(), KEY_QPS_VALUE, DEFAULT_QPS_VALUE);

        // 服务名加方法名构建Map的Key
        String serviceKey = String.join("_", url.getServiceKey(), invocation.getMethodName());
        // 尝试看看该服务是否有对应的计数对象
        AtomicInteger currentCount = COUNT_MAP.get(serviceKey);
        if (currentCount == null) {
            // 若没有对应的计数对象的话，则 putIfAbsent 会进行加锁控制，内部有并发锁控制
            COUNT_MAP.putIfAbsent(serviceKey, new AtomicInteger());
            currentCount = COUNT_MAP.get(serviceKey);
        }

        // 若当前的计数值大于或等于已配置的限流值的话，那么返回 false 表示无法获取计数资源
        if (currentCount.get() >= qpsValue) {
            return false;
        }

        // 能来到这里说明是可以获取锁资源的，那么就正常的加锁即可
        currentCount.incrementAndGet();
        return true;
    }

    // 通过JVM内存的处理方式，来进行计数资源释放
    private void releaseOfJvmLimit(URL url, Invocation invocation) {
        // 释放计数资源
        String serviceKey = String.join("_", url.getServiceKey(), invocation.getMethodName());
        AtomicInteger currentCount = COUNT_MAP.get(serviceKey);
        currentCount.decrementAndGet();
    }

    // 通过Redis的处理方式，来尝试是否能获取到限流计数资源
    private Boolean tryAcquireOfRedisLimit(URL url, Invocation invocation) {
//        // 从方法层面获取 qps.value 限流的标准容量，如果没配置则默认为 30
//        long qpsValue = url.getMethodParameter
//                (invocation.getMethodName(), KEY_QPS_VALUE, DEFAULT_QPS_VALUE);
//        // 服务名加方法名构建Map的Key
//        String serviceKey = String.join("_", url.getServiceKey(), invocation.getMethodName());
//
//        // 尝试看看该服务在 redis 中当前计数值是多少
//        int currentCount = NumberUtils.toInt(redisTemplate.opsForValue().get(serviceKey));
//        // 若当前的计数值大于或等于已配置的限流值的话，那么返回 false 表示无法获取计数资源
//        if (currentCount.get() >= qpsValue) {
//            return false;
//        }
//
//        // 能来到这里说明是可以获取锁资源的，那么就正常的加锁即可
//        redisTemplate.opsForValue().increment(serviceKey, 1);
        return true;
    }

    // 通过Redis的处理方式，来进行计数资源释放
    private void releaseOfRedisLimit(URL url, Invocation invocation) {
        // 释放计数资源
        String serviceKey = String.join("_", url.getServiceKey(), invocation.getMethodName());
//        redisTemplate.opsForValue().increment(serviceKey, -1);
    }
}