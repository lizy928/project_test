package com.dlion.testproject.collection;

import java.util.HashMap;

/**
 * DK1.8 之前 HashMap 底层是 数组和链表 结合在一起使用也就是 链表散列。HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash 值，
 * 然后通过 (n - 1) & hash 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，
 * 如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。
 * <p>
 * 所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的 hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。
 *
 * @author lzy
 * @date 2020/9/4
 */
public class HashMapTest {

    public static void main(String[] args) {
        System.out.println(10&123434343);
        HashMap<String, Object> result = new HashMap<>(1000);
        result.put("key",2);
        System.out.println(result.size());
    }


}
