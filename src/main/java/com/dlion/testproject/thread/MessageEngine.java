package com.dlion.testproject.thread;

import com.dlion.testproject.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 微信消息发送引擎
 *
 * @author 李正元
 * @date 2019/6/3
 */
@Component
public class MessageEngine extends Thread implements InitializingBean, ApplicationListener<ContextClosedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 控制任务运行的开关
     */
    private Boolean running = true;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("messageSendThreadPool")
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private CommonService commonService;

    @Override
    public void run() {
        while (running) {


            try {
                List<Object> results = redisTemplate.execute(new SessionCallback<List<Object>>() {
                    @Override
                    public List<Object> execute(RedisOperations operations) throws DataAccessException {
                        operations.multi();
                        operations.opsForList().range("testTHread", -10, -1);
                        operations.opsForList().trim("testTHread", 0, -10 - 1);
                        return operations.exec();
                    }
                });

                // 如果Redis中的有序集合是空的，则等待一段时间后再重新获取
                List<Object> objs = ((List<Object>) results.get(0));
                if (objs == null || objs.isEmpty()) {
                    Thread.sleep(100);
                    continue;
                }

                for (int i = objs.size(); i > 0; i--) {
                    while (taskExecutor.getActiveCount() >= taskExecutor.getMaxPoolSize()) {
                        Thread.sleep(100);
                    }
                    Map<String, Object> message = (Map<String, Object>) objs.get(i-1);
                    commonService.handle(message);

                }
            } catch (Exception e) {
                logger.error("微信消息发送引擎运行中发生异常", e);
            }
        }

    }

    @Override
    public void afterPropertiesSet() {
        logger.info("正在启动微信消息发送引擎...");
        this.start();
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        logger.info("正在关闭微信消息发送引擎...");
        this.running = false;
        taskExecutor.shutdown();
    }
}
