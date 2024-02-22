package com.hmilyylimh.cloud.threadpool.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-12-30
 */
@Slf4j
public class MonitorFixedThreadPool extends FixedThreadPool implements Runnable {

    private static final Set<ThreadPoolExecutor> EXECUTOR_SET = new HashSet<>();

    /** <h2>高水位线阈值</h2> **/
    private static final double HIGH_WATER_MARK = 0.85;


    public MonitorFixedThreadPool() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(
                        this,
                        0,
                        30,
                        TimeUnit.SECONDS
                );
    }

    @Override
    public Executor getExecutor(URL url) {
        Executor executor = super.getExecutor(url);

        if (executor instanceof ThreadPoolExecutor) {
            EXECUTOR_SET.add((ThreadPoolExecutor) executor);
        }

        return executor;
    }

    @Override
    public void run() {
        for (ThreadPoolExecutor executor : EXECUTOR_SET) {
            doCheck(executor);
        }
    }

    private void doCheck(ThreadPoolExecutor executor) {
        final int activeCount = executor.getActiveCount();
        int maximumPoolSize = executor.getMaximumPoolSize();
        double percent = activeCount / (maximumPoolSize * 1.0);

        if (percent > HIGH_WATER_MARK) {
            log.info("溢出高水位线：activeCount={}, maximumPoolSize={}, percent={}",
                    activeCount, maximumPoolSize, percent);

            CatUtils.logEvent("线程池溢出高水位线",
                    executor.getClass().getName(),
                    "1", buildCatLogDetails(executor));
        }
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

    private static class CatUtils {
        public static void logEvent(String type, String name, String status, String nameParams) {

        }
    }

    private String buildCatLogDetails(ThreadPoolExecutor executor) {
        return null;
    }
}
