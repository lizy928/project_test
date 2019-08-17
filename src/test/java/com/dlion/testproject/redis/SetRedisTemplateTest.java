package com.dlion.testproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
     * 判断 member 元素是否是集合 key 的成员
     */
    @Test
    public void isMember() {

        Boolean aBoolean = redisTemplate.opsForSet().isMember("setTest", "aaa");
        System.out.println(aBoolean);
    }

    /**
     * key对应的无序集合与otherKey对应的无序集合求交集
     */
    @Test
    public void intersect() {

        Set<Object> set = redisTemplate.opsForSet().intersect("setTest", "test1");
        System.out.println(set);
    }

    /**
     * key无序集合与otherkey无序集合的交集存储到destKey无序集合中
     */
    @Test
    public void intersectAndStore() {

        Long aLong = redisTemplate.opsForSet().intersectAndStore("setTest", "setTest1", "setTest3");
        System.out.println(aLong);
    }

    /**
     * -key对应的无序集合与多个otherKey对应的无序集合求交集存储到destKey无序集合中
     */
    public void intersectAndStoreColl() {

        List list = new ArrayList();
        Long aLong = redisTemplate.opsForSet().intersectAndStore("setTest", list, "setTest3");
        System.out.println(aLong);
    }

    /**
     * 11）Set<V> union(K key, K otherKey)     ----key无序集合与otherKey无序集合的并集
     */
    @Test
    public void union() {

        Set<Object> union = redisTemplate.opsForSet().union("setTest", "setTest1");
        System.out.println(union);
    }


    /**
     * 12）Set<V> union(K key, Collection<K> otherKeys)    ----key无序集合与多个otherKey无序集合的并集
     */
    @Test
    public void unionOther() {

        List list = new ArrayList();
        list.add("setTest1");
        Set union = redisTemplate.opsForSet().union("setTest", list);
        System.out.println(union);
    }


    /**
     * 13）Long unionAndStore(K key, K otherKey, K destKey)    ----key无序集合与otherkey无序集合的并集存储到destKey无序集合中
     */
    @Test
    public void unionAndStore() {

        Long aLong = redisTemplate.opsForSet().unionAndStore("setTest", "setTest1", "setTest2");
        System.out.println(aLong);
    }


    /**
     * 14）Long unionAndStore(K key, Collection<K> otherKeys, K destKey)
     * ----key无序集合与多个otherkey无序集合的并集存储到destKey无序集合中
     */
    @Test
    public void unionAndStoreOther() {

        List list = new ArrayList();
        Long aLong = redisTemplate.opsForSet().unionAndStore("setTest", list, "setTest1");
        System.out.println(aLong);
    }


    /**
     * 15）Set<V> difference(K key, K otherKey)
     * ----key无序集合与otherKey无序集合的差集
     */
    @Test
    public void difference() {

        Set<Object> difference = redisTemplate.opsForSet().difference("setTest", "setTest1");
        System.out.println(difference);
    }

    /**
     * 16）Set<V> difference(K key, Collection<K> otherKeys)
     * ----key无序集合与多个otherKey无序集合的差集
     */
    @Test
    public void diff() {

        List list = new ArrayList();
        Set difference = redisTemplate.opsForSet().difference("setTest", list);
        System.out.println(difference);
    }

    /**
     * 17）Long differenceAndStore(K key, K otherKey, K destKey)
     * ----key无序集合与otherkey无序集合的差集存储到destKey无序集合中
     */
    @Test
    public void differ() {

        Long aLong = redisTemplate.opsForSet().differenceAndStore("setTest", "setTest1", "setTest2");
        System.out.println(aLong);
    }

    /**
     * 18）Long differenceAndStore(K key, Collection<K> otherKeys, K destKey)
     * ----key无序集合与多个otherkey无序集合的差集存储到destKey无序集合中
     */
    @Test
    public void diffColl() {

        List list = new ArrayList();
        Long aLong = redisTemplate.opsForSet().differenceAndStore("", list, "");
        System.out.println(aLong);
    }

    /**
     * 19）Set<V> members(K key)
     * ----返回集合中的所有成员
     */
    @Test
    public void members() {

        Set<Object> setTest = redisTemplate.opsForSet().members("setTest");
        System.out.println(setTest);
    }

    /**
     * 20）V randomMember(K key)
     * ----随机获取key无序集合中的一个元素
     */
    @Test
    public void randomMember() {

        Object setTest = redisTemplate.opsForSet().randomMember("setTest");
        System.out.println(setTest);
    }

    /**
     * 21）Set<V> distinctRandomMembers(K key, long count)
     * ----获取多个key无序集合中的元素（去重），count表示个数
     */
    @Test
    public void distinctRandomMembers() {

        Set<Object> setTest = redisTemplate.opsForSet().distinctRandomMembers("setTest", 10);
        System.out.println(setTest);
    }

    /**
     * 22）List<V> randomMembers(K key, long count)
     * ----获取多个key无序集合中的元素，count表示个数
     */
    @Test
    public void randomMembers() {

        List<Object> setTest = redisTemplate.opsForSet().randomMembers("setTest", 10);
        System.out.println(setTest);
    }


    /**
     *     23）Cursor<V> scan(K key, ScanOptions options)
     *     ----遍历set
     */
    @Test
    public void scan(){
        ScanOptions options = ScanOptions.scanOptions().match("DL*").count(Integer.MAX_VALUE).build();
        Cursor<Map.Entry<Object, Object>> scan;
        Cursor<Object> cursor = redisTemplate.opsForSet().scan("", options);
        System.out.println(cursor);
    }


}
