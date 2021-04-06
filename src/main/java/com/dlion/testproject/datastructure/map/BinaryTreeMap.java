package com.dlion.testproject.datastructure.map;

/**
 * 文件描述
 *
 * @author lizy
 * @date 2021年04月05日 1:49 下午
 */
public class BinaryTreeMap<K, V> implements IMap<K, V> {

    private class Entry<K, V> implements Comparable<Entry<K, V>> {
        private K key;  //实现Comparable接口
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable) this.key).compareTo(o.key);
        }
    }

    private class Node {
        private Entry<K, V> data;
        private Node left;
        private Node right;

        public Node(Entry<K, V> data) {
            this.data = data;
        }

        public V addNode(Node newNode) {
            if (this.data.compareTo(newNode.data) < 0) {
                if (this.right == null) {
                    this.right = newNode;
                } else {
                    return this.right.addNode(newNode);
                }
            } else if (this.data.compareTo(newNode.data) > 0) {
                if (this.left == null) {
                    this.left = newNode;
                } else {
                    return this.left.addNode(newNode);
                }
            } else {
                V old = this.data.value;
                this.data.value = newNode.data.value;
                return old;
            }
            return null;
        }

        public V getNode(K key) {
            if (this.data.key.equals(key)) {
                return this.data.value;
            } else {
                if (((Comparable) this.data.key).compareTo(key) <= 0) {
                    if (this.right != null) {
                        return this.right.getNode(key);
                    } else {
                        return null;
                    }
                } else {
                    if (this.left != null) {
                        return this.left.getNode(key);
                    } else {
                        return null;
                    }
                }
            }

        }
    }

    private Node root;

    private int count;

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key和value不允许为空");
        }
        if (!(value instanceof Comparable)) {
            throw new ClassCastException("未实现Comparable接口");
        }
        count++;
        Entry<K, V> entry = new Entry<>(key, value);
        Node newNode = new Node(entry);
        if (this.root == null) {
            this.root = newNode;
            return null;
        } else {
            return this.root.addNode(newNode);
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("key不允许为空");
        }
        if (!(key instanceof Comparable)) {
            throw new ClassCastException("未实现Comparable接口");
        }

        return this.root.getNode(key);
    }

    @Override
    public int size() {
        return this.count;
    }
}
