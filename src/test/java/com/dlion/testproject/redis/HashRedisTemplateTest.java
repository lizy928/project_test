package com.dlion.testproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Redis的散列可以让用户将多个键值对存储到一个Redis键里面。
 * public interface HashOperations<H,HK,HV>
 * HashOperations提供一系列方法操作hash：
 *
 * @author 李正元
 * @date 2019/8/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashRedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置散列hashKey的值
     */
    @Test
    public void put() {

        redisTemplate.opsForHash().put("testHash", "token", 1);
        System.out.println(redisTemplate.opsForHash().get("testHash", "token"));
    }

    /**
     * 删除给定的哈希hashKeys
     */
    @Test
    public void delete() {

        redisTemplate.opsForHash().delete("testHash", "token");
    }




}
