package com.dlion.testproject.config;

import com.alibaba.acm.shaded.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;

/**
 * 线程池配置
 *
 * @author 李正元
 * @date 2019/6/4
 */
@Configuration
public class ThreadPoolConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public ThreadPoolTaskExecutor messageSendThreadPool() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(0);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("send-message-to-weixin-pool-%d")
                .setUncaughtExceptionHandler((thread, exception) ->
                        logger.error(thread.toString(), exception)
                ).build();

        executor.setThreadFactory(threadFactory);

        executor.initialize();
        return executor;
    }

}
