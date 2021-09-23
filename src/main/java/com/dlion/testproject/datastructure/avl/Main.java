package com.dlion.testproject.datastructure.avl;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("i-have-a-dream.txt", words)) {
            System.out.println("总单词：" + words.size());
            long startTime;
            long endTime;
            startTime = System.nanoTime();
            AvlTree<String> avl = new AvlTree<>();
            for (String word : words) {
                avl.add(word);
            }
            for(String word: words) {
                avl.contains(word);
            }
            endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("构建AVL耗时: " + time + " s");
            System.out.println("是否是平衡二叉树："+avl.isBalanced());
        }
        System.out.println();
    }
}
