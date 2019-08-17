package com.dlion.testproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author 李正元
 * @date 2019-08-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZSetRedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 新增一个有序集合，存在的话为false，不存在的话为true
     */
    @Test
    public void add() {

        Boolean aBoolean = redisTemplate.opsForZSet().add("ZsetTest", "Ztest1", 1);
        System.out.println(aBoolean);
        System.out.println(redisTemplate.opsForZSet().range("ZsetTest", 0, 10));
    }

    /**
     * 增一个有序集合
     */
    @Test
    public void addSet() {

        Set set = new HashSet();
        set.add("123");
        set.add(1);

        Long zsetTest = redisTemplate.opsForZSet().add("ZsetTest", set);
        System.out.println(zsetTest);
        System.out.println(redisTemplate.opsForZSet().range("ZsetTest", 0, 10));
    }

    /**
     * Long remove(K key, Object... values)
     * ----从有序集合中移除一个或者多个元素
     */
    @Test
    public void remove() {

        Long zsetTest = redisTemplate.opsForZSet().remove("ZsetTest", 1);
        System.out.println(zsetTest);
        System.out.println(redisTemplate.opsForZSet().range("ZsetTest", 0, 10));
    }

    /**
     * Double incrementScore(K key, V value, double delta)    ----增加元素的score值，并返回增加后的值
     */
    @Test
    public void incrementScore() {

        Double score = redisTemplate.opsForZSet().incrementScore("ZsetTest", 1, 1);
        System.out.println(score);
        System.out.println(redisTemplate.opsForZSet().range("ZsetTest", 0, 10));
    }

    /**
     * Long rank(K key, Object o)
     * ----返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rank() {

        Long zsetTest = redisTemplate.opsForZSet().rank("ZsetTest", 1);
        System.out.println(zsetTest);
    }

    /**
     * Long reverseRank(K key, Object o)
     * ----返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
     */
    @Test
    public void reverseRank() {

        Long zsetTest = redisTemplate.opsForZSet().reverseRank("ZsetTest", 1);
        System.out.println(zsetTest);
    }

    /**
     * Set<V> range(K key, long start, long end)
     * ----通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void range() {

        Set<Object> zsetTest = redisTemplate.opsForZSet().range("ZsetTest", 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Set<TypedTuple<V>> rangeWithScores(K key, long start, long end)
     * ----通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rangeWithScores() {

        Set<ZSetOperations.TypedTuple<Object>> zsetTest = redisTemplate.opsForZSet().rangeWithScores("ZsetTest", 0, 10);
        System.out.println(zsetTest);
    }

    /**
     * Set<V> rangeByScore(K key, double min, double max）
     * ----通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rangeByScore() {

        Set<Object> zsetTest = redisTemplate.opsForZSet().rangeByScore("ZsetTest", 0, 10);
        System.out.println(zsetTest);
    }

    /**
     * Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max)
     * ----通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rangeByScoreWithScores() {

        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().rangeByScoreWithScores("ZsetTest", 1, 10);
        System.out.println(typedTuples);
    }

    /**
     * Set<V> rangeByScore(K key, double min, double max, long offset, long count)
     * ----通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rangeByScore1() {

        Set<Object> zsetTest = redisTemplate.opsForZSet().rangeByScore("ZsetTest", 1, 10, 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max, long offset, long count)   
     * ----通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rangeByScoreWithScores1() {

        Set<ZSetOperations.TypedTuple<Object>> zsetTest = redisTemplate.opsForZSet().rangeByScoreWithScores("ZsetTest", 1, 10, 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Set<V> reverseRange(K key, long start, long end)
     * ----通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
     */
    @Test
    public void reverseRange() {

        Set<Object> objects = redisTemplate.opsForZSet().reverseRange("ZsetTest", 1, 10);
        System.out.println(objects);
    }

    /**
     * Set<TypedTuple<V>> reverseRangeWithScores(K key, long start, long end)
     * ----通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小)顺序排列
     */
    @Test
    public void reverseRangeWithScores() {

        Set<ZSetOperations.TypedTuple<Object>> zsetTest = redisTemplate.opsForZSet().reverseRangeWithScores("ZsetTest", 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Long count(K key, double min, double max)
     * ----通过分数返回有序集合指定区间内的成员个数
     */
    @Test
    public void count() {

        Long zsetTest = redisTemplate.opsForZSet().count("ZsetTest", 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Long zCard(K key)
     * ----获取有序集合的成员数
     */
    @Test
    public void zCard() {

        Long zsetTest = redisTemplate.opsForZSet().zCard("ZsetTest");
        System.out.println(zsetTest);
    }

    /**
     * Double score(K key, Object o)
     * ----获取指定成员的score值
     */
    @Test
    public void score() {

        Double zsetTest = redisTemplate.opsForZSet().score("ZsetTest", 1);
        System.out.println(zsetTest);
    }

    /**
     * Long removeRange(K key, long start, long end)
     * ----移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void removeRange() {

        Long zsetTest = redisTemplate.opsForZSet().removeRange("ZsetTest", 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Long removeRangeByScore(K key, double min, double max)
     * ----根据指定的score值得范围来移除成员
     */
    @Test
    public void removeRangeByScore() {

        Long zsetTest = redisTemplate.opsForZSet().removeRangeByScore("ZsetTest", 1, 10);
        System.out.println(zsetTest);
    }

    /**
     * Long unionAndStore(K key, K otherKey, K destKey)
     * ----计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
     */
    @Test
    public void unionAndStore() {

        Long store = redisTemplate.opsForZSet().unionAndStore("ZsetTest", "ZsetTest1", "ZsetTest2");
        System.out.println(store);
    }

    /**
     * Long unionAndStore(K key, Collection<K> otherKeys, K destKey)
     * ----计算给定的多个有序集的并集，并存储在新的 destKey中
     */
    @Test
    public void unionAndStore1() {

        List list = new ArrayList();
        Long aLong = redisTemplate.opsForZSet().unionAndStore("ZsetTest", list, "ZsetTest2");
        System.out.println(aLong);
    }

    /**
     * Long intersectAndStore(K key, K otherKey, K destKey)
     * ----计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
     */
    @Test
    public void intersectAndStore() {

        Long aLong = redisTemplate.opsForZSet().intersectAndStore("ZsetTest", "ZsetTest1", "ZsetTest2");
        System.out.println(aLong);
    }

    /**
     * Long intersectAndStore(K key, Collection<K> otherKeys, K destKey)
     * ----计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
     */
    @Test
    public void intersectAndStore1(){

        List list = new ArrayList();
        redisTemplate.opsForZSet().intersectAndStore("ZsetTest", list, "ZsetTest2");
    }

    /**
     * Cursor<TypedTuple<V>> scan(K key, ScanOptions options)  
     *   ----遍历zset
     */
    @Test
    public void acan(){

        ScanOptions options = ScanOptions.scanOptions().match("DL*").count(Integer.MAX_VALUE).build();
        Cursor<ZSetOperations.TypedTuple<Object>> testHash = redisTemplate.opsForZSet().scan("testHash", options);
        System.out.println(testHash);
    }



}
