package com.dlion.testproject.scheduledtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 如果你想要你的代码并行执行的话，还可以通过@EnableAsync 和 @Async 这两个注解实现
 *
 * @author lzy
 * @date 2020/10/15
 */
@Component
@EnableAsync
public class AsyncScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(AsyncScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedDelay：固定延迟执行。距离上一次调用成功后2秒才执。
     */
    @Async
    @Scheduled(fixedDelay = 2000)
    public void reportCurrentTimeWithFixedDelay() {
        try {
            TimeUnit.SECONDS.sleep(3);
            log.info("Fixed Delay Task : The time is now {}", dateFormat.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
