package com.dlion.testproject.thread;

import com.alibaba.acm.shaded.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author 李正元
 * @date 2019/8/29
 */
@Service
public class ThreadFactoryService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final int EXECUTOR_THREAD_COUNT = 10;

    private ConcurrentHashMap<Integer, ExecutorService> executors = new ConcurrentHashMap<>();

    /**
     * 根据enterpriseId和cno获取一个线程执行器
     *
     * @param enterpriseId 企业Id
     * @param cno          座席号
     * @return 线程执行器
     */
    public ExecutorService getExecutor(Integer enterpriseId, String cno) {

        String key = enterpriseId.toString();

        if (cno != null) {
            key = key + cno;
        }
        int hashCode = Math.abs(key.hashCode());
        int keyCode = hashCode % EXECUTOR_THREAD_COUNT;

        ExecutorService executor = executors.get(keyCode);

        if (executor == null) {
            // 创建一个使用单个线程的 Executor，以队列方式来运行分配给该线程的任务
            // 自定义ThreadFactory：自定义线程名称、自定义UncaughtExceptionHandler方便排查异常
            ThreadFactory threadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("weibo-message-executor-pool-%d")
                    .setUncaughtExceptionHandler((thread, exception) ->
                            logger.error(thread.toString(), exception)
                    ).build();

            executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1024), threadFactory);

            executors.put(keyCode, executor);
        }

        return executor;
    }


}
