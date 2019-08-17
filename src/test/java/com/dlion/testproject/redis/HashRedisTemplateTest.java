package com.dlion.testproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

    /**
     * 确定哈希hashKey是否存在
     */
    @Test
    public void hasKey() {

        System.out.println(redisTemplate.opsForHash().hasKey("testHash", "token"));
    }

    /**
     * 从哈希中获取给定hashKey的值
     */
    @Test
    public void multiGet() {

        List keys = new ArrayList<>();
        keys.add("token");
        keys.add("test");
        redisTemplate.opsForHash().multiGet("testHash", keys);
    }

    /**
     * 通过给定的delta增加散列hashKey的值（整型）
     */
    @Test
    public void increment() {

        redisTemplate.opsForHash().increment("testHash", "num", 1);
    }

    /**
     * 获取key所对应的散列表的key
     */
    @Test
    public void keys() {

        Set<Object> set = redisTemplate.opsForHash().keys("testHash");
        set.forEach(e -> {
            System.out.println(e);
        });
    }

    /**
     * 获取key所对应的散列表的大小个数
     */
    @Test
    public void size() {
        Long size = redisTemplate.opsForHash().size("testHash");
        System.out.println(size);
    }

    /**
     * -使用m中提供的多个散列字段设置到key对应的散列表中
     */
    @Test
    public void putAll() {

        Map<String, Object> data = new HashMap<>();
        data.put("1","1");
        data.put("2","2");
        redisTemplate.opsForHash().putAll("testHash",data);
    }

    /**
     * 仅当hashKey不存在时才设置散列hashKey的值
     */
    @Test
    public void putIfAbsent(){

        Boolean aBoolean = redisTemplate.opsForHash().putIfAbsent("testHash", "token", 1);
        System.out.println(aBoolean);
    }

    /**
     * 获取整个哈希存储的值根据密钥
     */
    @Test
    public void values(){
        redisTemplate.opsForHash().put("testHash","1",1);
        redisTemplate.opsForHash().put("testHash","2",2);
        List<Object> list = redisTemplate.opsForHash().values("testHash");
        list.stream().forEach(e->{
            System.out.println(e);
        });
    }

    /**
     * 获取整个哈希存储根据密钥
     */
    @Test
    public void entries(){

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("testHash");
        System.out.println(entries);
    }

    /**
     * 使用Cursor在key的hash中迭代，相当于迭代器
     */
    @Test
    public void scan(){

        ScanOptions options = ScanOptions.scanOptions().match("DL*").count(Integer.MAX_VALUE).build();
        Cursor<Map.Entry<Object, Object>> scan;
        scan = redisTemplate.opsForHash().scan("testHash", options);
        System.out.println(scan);
    }




}
