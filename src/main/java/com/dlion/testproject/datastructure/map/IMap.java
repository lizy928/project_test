package com.dlion.testproject.datastructure.map;

/**
 * 定义一个树状结构存储的接口，实现数据的保存和查询
 *
 * @author lizy
 * @date 2021年04月05日 1:46 下午
 */
public interface IMap<K, V> {

    /**
     * 保存
     *
     * @param key   唯一键
     * @param value 值
     * @return
     */
    public V put(K key, V value);

    /**
     * 查询
     *
     * @param key
     * @return
     */
    public V get(K key);

    public int size();

    public static <K, V> IMap<K, V> getInstance(){
        return new BinaryTreeMap<>();
    }
}
