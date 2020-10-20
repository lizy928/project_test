package com.dlion.testproject.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lzy
 * @date 2020/10/19
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    /**
     * 使用onApplicationEvent接收消息
     * @param event
     */
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMessage();
        System.out.println("接收到的信息是："+msg);
    }

}
