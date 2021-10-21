package com.dlion.testproject.algorithm.tree.binarytreetraversal;

import java.util.*;

/**
 * 二叉树前序遍历
 */
public class PreOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<Integer> list = preorderTraversal(root);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public static void traversal(TreeNode treeNode, List<Integer> list){
        if(treeNode == null){
            return ;
        }
        list.add(treeNode.val);
        traversal(treeNode.left, list);
        traversal(treeNode.right, list);
    }

    /**
     *
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

