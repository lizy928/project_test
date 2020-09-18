package com.dlion.testproject.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 *
 * 避免使用Executors  类的 newFixedThreadPool 和 newCachedThreadPool ，因为可能会有 OOM 的风
 * Executors 返回线程池对象的弊端如下：
 *
 * FixedThreadPool 和 SingleThreadExecutor ： 允许请求的队列长度为 Integer.MAX_VALUE,可能堆积大量的请求，从而导致 OOM。
 * CachedThreadPool 和 ScheduledThreadPool ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致 OOM。
 * 说白了就是：使用有界队列，控制线程创建数量。
 *
 * 除了避免 OOM 的原因之外，不推荐使用 Executors 提供的两种快捷的线程池的原因还有：
 *
 * 实际使用中需要根据自己机器的性能、业务场景来手动配置线程池的参数比如核心线程数、使用的任务队列、饱和策略等等。
 * 我们应该显示地给我们的线程池命名，这样有助于我们定位问题。
 *
 *
 * 降低资源消耗：通过池化技术重复利用已创建的线程，降低线程创建和销毁造成的损耗。
 * 提高响应速度：任务到达时，无需等待线程创建即可立即执行。
 * 提高线程的可管理性：线程是稀缺资源，如果无限制创建，不仅会消耗系统资源，还会因为线程的不合理分布导致资源调度失衡，降低系统的稳定性。使用线程池可以进行统一的分配、调优和监控。
 * 提供更多更强大的功能：线程池具备可拓展性，允许开发人员向其中增加更多的功能。比如延时定时线程池ScheduledThreadPoolExecutor，就允许任务延期执行或定期执行
 *
 * @author lzy
 * @date 2020/9/4
 */
@Slf4j
public class Main {

    /**
     * 打印线程池的状态
     *
     * @param threadPool 线程池对象
     */
    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks : {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                new ThreadPoolExecutor.CallerRunsPolicy());
        printThreadPoolStatus(executor);


    }
}
