package com.dlion.testproject.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 李正元
 * @date 2019/8/28
 */
@Service
public class CommonService {


    public void handle(Map<String, Object> map) {
        System.out.println("handle:" + map);
        send(map);
    }

    public void send(Map<String, Object> map){
        System.out.println("send:" + map);
    }
}
