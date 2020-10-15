package com.dlion.testproject.scheduledtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 默认情况下，@Scheduled任务都在Spring创建的大小为1的默认线程池中执行，你可以通过在加了@Scheduled注解的方法里加上下面这段代码来验证。
 * logger.info("Current Thread : {}", Thread.currentThread().getName());
 *
 * @author lzy
 * @date 2020/10/15
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedRate：固定速率执行。每5秒执行一次。
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimeWithFixedRate() {
        log.info("Current Thread : {}", Thread.currentThread().getName());
        log.info("Fixed Rate Task : The time is now {}", dateFormat.format(new Date()));
    }

    /**
     * initialDelay:初始延迟。任务的第一次执行将延迟5秒，然后将以5秒的固定间隔执行。
     */
    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void reportCurrentTimeWithInitialDelay() {
        log.info("Fixed Rate Task with Initial Delay : The time is now {}", dateFormat.format(new Date()));
    }

    /**
     * cron：使用Cron表达式。　每分钟的1，2秒运行
     */
    @Scheduled(cron = "1-2 * * * * ? ")
    public void reportCurrentTimeWithCronExpression() {
        log.info("Cron Expression: The time is now {}", dateFormat.format(new Date()));
    }


}
