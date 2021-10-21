package com.dlion.testproject.algorithm.tree.binarytreetraversal;

import java.util.*;

/**
 * 二叉树-中序遍历
 *
 */
public class MidOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<Integer> list = midOrderTraversal3(root);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public static List<Integer> midOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }

        }
        return list;
    }

    public static List<Integer> midOrderTraversal3(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
           while (root != null) {
               stack.push(root);
               root = root.left;
           }
           root = stack.pop();
           list.add(root.val);
           root = root.right;
        }
        return list;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> midOrderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public static void traversal(TreeNode treeNode, List list){
        if(treeNode == null ){
            return;
        }
        traversal(treeNode.left, list);
        list.add(treeNode.val);
        traversal(treeNode.right, list);
    }



}
