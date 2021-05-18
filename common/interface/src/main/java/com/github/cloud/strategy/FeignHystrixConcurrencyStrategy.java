package com.github.cloud.strategy;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : glw
 * @datetime : 2021/5/18 11:21
 * @description : 自定义 hystrix 并发策略，解决线程池隔离状态下，统一拦截器获取不到 request 问题
 */
@Slf4j
public class FeignHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    private HystrixConcurrencyStrategy strategy;

    public FeignHystrixConcurrencyStrategy() {
        try {
            this.strategy = HystrixPlugins.getInstance().getConcurrencyStrategy();
            if (this.strategy instanceof FeignHystrixConcurrencyStrategy) {
                return;
            }
            HystrixCommandExecutionHook hook = HystrixPlugins.getInstance().getCommandExecutionHook();
            HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
            HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
            HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
            this.log(eventNotifier, metricsPublisher, propertiesStrategy);

            HystrixPlugins.reset();

            HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
            HystrixPlugins.getInstance().registerCommandExecutionHook(hook);
            HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
            HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
            HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        } catch (Exception e) {
            log.error("Failed to register Sleuth Hystrix Concurrency Strategy, Exception = {}", e.getMessage());
        }
    }

    private void log(HystrixEventNotifier eventNotifier, HystrixMetricsPublisher metricsPublisher, HystrixPropertiesStrategy propertiesStrategy) {
        if (log.isDebugEnabled()) {
            log.debug("Current Hystrix plugin configuration is [concurrencyStrategy = {}, eventNotifier = {}, metricsPublisher = {}, propertiesStrategy = {}]", this.strategy, eventNotifier, metricsPublisher, propertiesStrategy);
            log.debug("Registering Sleuth Hystrix Concurrency Strategy.");
        }
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return this.strategy.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolProperties threadPoolProperties) {
        return this.strategy.getThreadPool(threadPoolKey, threadPoolProperties);
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return this.strategy.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return new WrapperCallable<>(callable, requestAttributes);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return this.strategy.getRequestVariable(rv);
    }

    static class WrapperCallable<T> implements Callable<T> {

        private final Callable<T> target;
        private final RequestAttributes requestAttributes;

        public WrapperCallable(Callable<T> target, RequestAttributes requestAttributes) {
            this.target = target;
            this.requestAttributes = requestAttributes;
        }

        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(this.requestAttributes);
                return this.target.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }
}
