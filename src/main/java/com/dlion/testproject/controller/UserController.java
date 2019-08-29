package com.dlion.testproject.controller;

import com.dlion.testproject.annotation.ChatClintPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李正元
 * @date 2019/8/16
 */
@RestController
public class UserController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/getUser")
    @ChatClintPermission
    public Object getUser() {

        for (int i = 0; i < 50; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1", "value:" + i);
            System.out.println("map:" + i);
            redisTemplate.opsForList().leftPush("testTHread", map);
        }

       return "success";
    }

    @RequestMapping("getResult")
    public Object getResult(){

        List<Object> results = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForList().range("testTHread", -50, -1);
                //operations.opsForList().trim("list", 0, -10 - 1);
                return operations.exec();
            }
        });

        return results;
    }


}
