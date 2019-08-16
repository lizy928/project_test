package com.dlion.testproject.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author 李正元
 * @date 2019/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListRedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
     */
    @Test
    public void leftPush() {

        redisTemplate.opsForList().leftPush("list", "java");
        redisTemplate.opsForList().leftPush("list", "python");
        System.out.println(redisTemplate.opsForList().leftPush("list", "c++"));
        //  3  返回的结果为推送操作后的列表的长度
    }

    /**
     * -批量把一个数组插入到列表中
     */
    @Test
    public void leftPushAll() {

        //Redis Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
        // 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
        String[] stringarrays = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("listarray", stringarrays);
        System.out.println(redisTemplate.opsForList().range("listarray", 0, -1));
        //[3, 2, 1]
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     */
    @Test
    public void leftPushIfPresent() {

        System.out.println(redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "aa")); //0
        System.out.println(redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "bb")); //0
        System.out.println(redisTemplate.opsForList().leftPush("leftPushIfPresent", "aa"));          //1
        System.out.println(redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "bb")); //2
    }

    /**
     * 把value值放到key对应列表中pivot值的左面，如果pivot值存在的话
     */
    @Test
    public void leftPushPivot() {

        redisTemplate.opsForList().leftPush("list", "java", "oc");
        System.out.print(redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 右侧插入，与left左侧插入同理，方法类似
     */
    @Test
    public void rightPush() {
        redisTemplate.opsForList().leftPush("list", "c++");
        redisTemplate.opsForList().rightPush("list", "c#");
        System.out.print(redisTemplate.opsForList().range("list", 0, -1));
    }


    /**
     * 在列表中index的位置设置value值
     */
    @Test
    public void set() {

        redisTemplate.opsForList().set("list", 2, "");
    }

    /**
     * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素
     */
    @Test
    public void range() {

        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误
     */
    @Test
    public void size() {

        System.out.println(redisTemplate.opsForList().size("list"));
    }

    /**
     * 根据下表获取列表中的值，下标是从0开始的
     */
    @Test
    public void index() {

        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        System.out.println(redisTemplate.opsForList().index("listRight", 2));
    }

    /**
     * 弹出最左边/最右边的元素，弹出之后该值在列表中将不复存在
     */
    @Test
    public void leftPop() {

        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        System.out.println(redisTemplate.opsForList().leftPop("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * 移出并获取列表的第一个/最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     */
    @Test
    public void leftPopTimeUnit() {

        System.out.println(redisTemplate.opsForList().leftPop("list", 20, TimeUnit.SECONDS));
    }

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     */
    @Test
    public void rightPopAndLeftPush(){

        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        redisTemplate.opsForList().rightPopAndLeftPush("list","rightPopAndLeftPush");
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        System.out.println(redisTemplate.opsForList().range("rightPopAndLeftPush",0,-1));
    }

    /**
     * 修剪现有列表，使其只包含指定的指定范围的元素，起始和停止都是基于0的索引
     */
    @Test
    public void trim(){

        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        redisTemplate.opsForList().trim("list",1,-1);//裁剪第一个元素
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件
     */
    @Test
    public void remove(){

        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
        redisTemplate.opsForList().remove("listRight",1,"setValue");
        //将删除列表中存储的列表中第一次次出现的“setValue”。
        System.out.println(redisTemplate.opsForList().range("listRight",0,-1));
    }




}
