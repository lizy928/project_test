package com.dlion.testproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author 李正元
 * @date 2019/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SetRedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 无序集合中添加元素，返回添加个数，也可以直接在add里面添加多个值 如：
     */
    @Test
    public void add() {

        Long add = redisTemplate.opsForSet().add("setTest", "aaa", "bbb");
        System.out.println(add);
    }

    /**
     * 移除集合中一个或多个成员
     */
    @Test
    public void remove() {

        Long remove = redisTemplate.opsForSet().remove("setTest", "aaa");
        System.out.println(remove);
    }

    /**
     * 移除并返回集合中的一个随机元素
     */
    @Test
    public void pop() {

        Object pop = redisTemplate.opsForSet().pop("setTest");
        System.out.println(pop);
    }

    /**
     * -将 member 元素从 source 集合移动到 destination 集合
     */
    @Test
    public void move() {

        Boolean move = redisTemplate.opsForSet().move("setTest", "aaa", "test1");
        System.out.println(move);
    }

    /**
     * 无序集合的大小长度
     */
    @Test
    public void size() {

        Long size = redisTemplate.opsForSet().size("setTest");
        System.out.println(size);
    }

    /**
     *判断 member 元素是否是集合 key 的成员
     */
    @Test
    public void isMember(){

        Boolean aBoolean = redisTemplate.opsForSet().isMember("setTest", "aaa");
        System.out.println(aBoolean);
    }

    /**
     * key对应的无序集合与otherKey对应的无序集合求交集
     */
    @Test
    public void intersect(){

        Set<Object> set = redisTemplate.opsForSet().intersect("setTest", "test1");
        System.out.println(set);
    }




}
