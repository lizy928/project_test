package com.dlion.testproject.redis;

import com.dlion.testproject.model.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("test1", "2");
    }

    /**
     * timeout:过期时间；  unit:时间单位
     */
    @Test
    public void setTimeOut() {
        redisTemplate.opsForValue().set("test2", "2", 100L, TimeUnit.SECONDS);
    }

    /**
     * offset：value值覆写偏移量
     */
    @Test
    public void setOffset() {
        redisTemplate.opsForValue().set("test3", "hello world");
        redisTemplate.opsForValue().set("test3", "redis", 6);
        System.out.println(redisTemplate.opsForValue().get("test3"));
        // hello world -------> hello redis
    }

    /**
     * 该方法不会覆盖已存在数据
     */
    @Test
    public void setIfAbsent() {
        redisTemplate.opsForValue().set("test4", "test4");
        redisTemplate.opsForValue().setIfAbsent("test4", "test44");  // false
        redisTemplate.opsForValue().setIfAbsent("test44", "test44");  // true
        System.out.println(redisTemplate.opsForValue().get("test4"));       // test4
    }

    /**
     * 为多个键分别设置它们的值
     */
    @Test
    public void multiSet() {

        Map<String, String> data = new HashMap<>();
        data.put("multi1", "multi1");
        data.put("multi2", "multi2");
        data.put("multi3", "multi3");

        redisTemplate.opsForValue().multiSet(data);
    }

    /**
     * 获取多个键的值
     */
    @Test
    public void multiGet() {

        List<String> keys = new ArrayList<>();

        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");

        System.out.println(redisTemplate.opsForValue().multiGet(keys));
    }

    /**
     * 为多个键分别设置它们的值，如果存在则返回false，不存在返回true
     */
    @Test
    public void multiSetIfAbsent() {

        Map<String, String> maps = new HashMap<>();
        maps.put("multi11", "multi11");
        maps.put("multi22", "multi22");
        maps.put("multi33", "multi33");
        Map<String, String> maps2 = new HashMap<>();
        maps2.put("multi1", "multi1");
        maps2.put("multi2", "multi2");
        maps2.put("multi3", "multi3");
        System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(maps)); //  true
        System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(maps2));//  false

    }

    /**
     * 数值增加（支持整数）
     */
    @Test
    public void increment() {

        redisTemplate.opsForValue().increment("increment1");
        System.out.println(redisTemplate.opsForValue().get("increment1"));

        // 1,2,3,4
    }

    /**
     * 数值增加 Long
     */
    @Test
    public void incrementLong() {

        redisTemplate.opsForValue().increment("increment2", 100L);
        System.out.println(redisTemplate.opsForValue().get("increment2"));
        //100,200,300,400
    }

    /**
     * 数值增加 double
     */
    @Test
    public void incrementDouble() {

        redisTemplate.opsForValue().increment("increment3", 100.01);
        System.out.println(redisTemplate.opsForValue().get("increment3"));
        //100.01 200.02
    }

    /**
     * -如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。
     * 如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET
     */
    @Test
    public void append() {

        redisTemplate.opsForValue().append("appendTest", "Hello");
        System.out.println(redisTemplate.opsForValue().get("appendTest"));
        redisTemplate.opsForValue().append("appendTest", "world");
        System.out.println(redisTemplate.opsForValue().get("appendTest"));
        // Hello Helloworld
    }

    /**
     * 查询数据
     */
    @Test
    public void get() {

        redisTemplate.opsForValue().set("key", "hello world");
        System.out.println("***************" + redisTemplate.opsForValue().get("key"));
        //***************hello world
    }

    /**
     * -设置键的字符串值并返回其旧值
     */
    @Test
    public void getAndSet() {

        redisTemplate.opsForValue().set("getSetTest", "test");
        System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest", "test2"));
        // test
    }

    /**
     * 截取key所对应的value字符串
     */
    @Test
    public void subtringGet() {

        redisTemplate.opsForValue().set("subtringGet", "subtringGet");
        System.out.println("*********" + redisTemplate.opsForValue().get("subtringGet", 0, 5));  //*********subtri
        System.out.println("*********" + redisTemplate.opsForValue().get("subtringGet", 1, -2)); //*********ubtringGe
    }

    /**
     * 返回key所对应的value值得长度
     */
    @Test
    public void size() {

        redisTemplate.opsForValue().set("testSize", "hello world");
        System.out.println("***************" + redisTemplate.opsForValue().size("testSize")); //***************11
    }

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)，
     * key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
     */
    @Test
    public void setBit() {

        redisTemplate.opsForValue().set("bitTest", "a");
        // 'a' 的ASCII码是 97。转换为二进制是：01100001
        // 'b' 的ASCII码是 98 转换为二进制是：01100010
        // 'c' 的ASCII码是 99 转换为二进制是：01100011
        //因为二进制只有0和1，在setbit中true为1，false为0，因此我要变为'b'的话第六位设置为1，第七位设置为0
        redisTemplate.opsForValue().setBit("bitTest", 6, true);
        redisTemplate.opsForValue().setBit("bitTest", 7, false);
        System.out.println(redisTemplate.opsForValue().get("bitTest")); //b
    }

    /**
     * 获取键对应值的ascii码的在offset处位值
     */
    @Test
    public void getBit() {

        System.out.println(redisTemplate.opsForValue().getBit("bitTest", 7)); //false
    }

    @Test
    public void getTime() {
        String expire = "1568789014";
        long CHAT_EXPIRE_TIME_OUT = 60 * 60 * 47;
        expire = String.valueOf(System.currentTimeMillis() / 1000 + CHAT_EXPIRE_TIME_OUT);
        if (StringUtils.isEmpty(expire) || Long.valueOf(expire) <= System.currentTimeMillis() / 1000) {
            System.out.println("访客48小时内未有聊天记录");
        } else {
            System.out.println("继续执行了");
        }
    }

    @Test
    public void testSort() {

        User user5 = new User();
        user5.setId(5);
        User user = new User();
        user.setId(1);
        User user2 = new User();
        user2.setId(2);
        User user3 = new User();
        user3.setId(3);
        User user4 = new User();
        user4.setId(4);


        List<User> list = new ArrayList<>();
        list.add(user3);
        list.add(user);
        list.add(user4);
        list.add(user5);
        list.add(user2);
        list.add(user2);
        list.add(user2);

        //Arrays.sort(list, (user1 , user6) -> Integer.compare(user.getId(), ));

        List<User> newList = list.stream().sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());

        System.out.println(newList);

        list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(User::getId))
                ), ArrayList::new)
        );

        System.out.println(list);

    }

}
