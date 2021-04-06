package com.dlion.testproject.datastructure.binarytree.sortTree;

public interface BinaryTree<E> {

    void add(E data);

    int size();

    Object[] toArray();

    boolean contains(E data);

    void remove(E data);
}
