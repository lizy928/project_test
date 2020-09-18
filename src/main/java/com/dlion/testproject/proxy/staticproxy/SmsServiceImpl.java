package com.dlion.testproject.proxy.staticproxy;

/**
 * @author lzy
 * @date 2020/9/4
 */
public class SmsServiceImpl implements SmsService{

    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
