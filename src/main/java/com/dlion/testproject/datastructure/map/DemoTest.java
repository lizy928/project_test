package com.dlion.testproject.datastructure.map;

/**
 * 文件描述
 *
 * @author lizy
 * @date 2021年04月05日 3:58 下午
 */
public class DemoTest {

    public static void main(String[] args) {
        IMap<String, Integer> iMap = IMap.getInstance();
        iMap.put("1", 2);
        iMap.put("2", 2);
        iMap.put("5", 5);
        iMap.put("9", 9);

        System.out.println("获取map中Key为2的数据:" + iMap.get("2"));
    }
}
