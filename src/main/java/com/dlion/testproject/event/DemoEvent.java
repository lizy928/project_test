package com.dlion.testproject.event;

import org.springframework.context.ApplicationEvent;

/**
 * 定义一个事件,继承自ApplicationEvent并且写相应的构造函数
 *
 * @author lzy
 * @date 2020/10/19
 */
public class DemoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private String message;

    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
