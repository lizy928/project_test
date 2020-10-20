package com.dlion.testproject.event;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发布事件，可以通过ApplicationEventPublisher  的 publishEvent() 方法发布消息
 *
 * @author lzy
 * @date 2020/10/19
 */
@Component
public class DemoPublisher {

    @Resource
    ApplicationContext applicationContext;

    public void publish(String message) {
        //发布事件
        applicationContext.publishEvent(new DemoEvent(this, message));

    }
}
