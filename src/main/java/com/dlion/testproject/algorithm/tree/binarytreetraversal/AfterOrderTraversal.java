package com.dlion.testproject.algorithm.tree.binarytreetraversal;

import java.util.*;

/**
 * 后序遍历
 *
 */
public class AfterOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<Integer> list = afterOrderTraversal(root);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public static List<Integer> afterOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null){
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
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
    public static List<Integer> afterOrderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public static void traversal(TreeNode treeNode, List list){
        if(treeNode == null ){
            return;
        }
        traversal(treeNode.left, list);
        traversal(treeNode.right, list);
        list.add(treeNode.val);
    }


}
