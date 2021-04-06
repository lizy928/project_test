package com.dlion.testproject.datastructure.binarytree.sortTree;


import java.util.Arrays;

/**
 * 排序二叉树的实现
 *
 * @author lizy
 */
public class BinaryTreeImpl<E> implements BinaryTree<E> {

    class Node {
        private Comparable<E> data;

        private Node left;

        private Node right;

        private Node parent;

        Node(Comparable<E> data) {
            this.data = data;
        }

        void toArrayNode() {
            if (this.left != null) {
                this.left.toArrayNode();
            }
            BinaryTreeImpl.this.data[BinaryTreeImpl.this.foot++] = (E) this.data;
            if (this.right != null) {
                this.right.toArrayNode();
            }
        }

        Node contaions(E data) {
            if (this.data.compareTo(data) == 0) {
                return this;
            }
            if (this.data.compareTo(data) < 0) {
                if (this.right != null) {
                    return this.right.contaions(data);
                } else {
                    return null;
                }
            }
            if (this.data.compareTo(data) > 0) {
                if (this.left != null) {
                    return this.left.contaions(data);
                } else {
                    return null;
                }
            }
            return null;
        }

    }

    private Node root;

    private int count;

    private int foot;

    private Object[] data;

    @Override
    public void add(E data) {
        if (data == null) {
            throw new NullPointerException("节点数据不允许为空！");
        }
        if (!(data instanceof Comparable)) {
            throw new ClassCastException("必须为实现Comparable接口!");
        }
        Node newNode = new Node((Comparable<E>) data);
        if (this.root == null) {
            this.root = newNode;
        } else {
            Node currentNode = this.root;
            while (currentNode != newNode) {
                if (currentNode.data.compareTo(data) <= 0) { //比根节点大
                    if (currentNode.right != null) {
                        currentNode = currentNode.right;
                    } else {
                        currentNode.right = newNode;
                        newNode.parent = currentNode;
                        currentNode = newNode;
                    }
                } else {
                    if (currentNode.left != null) {
                        currentNode = currentNode.left;
                    } else {
                        currentNode.left = newNode;
                        newNode.parent = currentNode;
                        currentNode = newNode;
                    }
                }
            }
        }
        this.count++;
    }

    public int size() {
        return this.count;
    }

    @Override
    public Object[] toArray() {
        if (this.size() == 0) {
            return new Object[0];
        }
        this.foot = 0;
        this.data = new Object[this.size()];
        this.root.toArrayNode();
        return this.data;
    }

    @Override
    public boolean contains(E data) {
        if (this.size() == 0) {
            return false;
        }
        if (!(data instanceof Comparable)) {
            throw new ClassCastException("未实现Comparable接口");
        }
        return this.root.contaions(data) != null;
    }

    @Override
    public void remove(E data) {
        if(data == null){
            throw new NullPointerException("不允许为空");
        }
        if(!(data instanceof Comparable)){
            throw new ClassCastException("未实现Comparable接口");
        }
        if(this.contains(data)){
            if(this.root.data.compareTo(data) == 0){
                Node node = moveNode(data);
                if(node != null){
                    this.root = node;
                }
            } else {
                this.moveNode(data);
            }
        }
        this.count --;
    }

    private Node moveNode(E data){
        Node moveSubNode = null;
        Node delteNode = this.root.contaions(data);
        //要删除的节点为叶子节点
        if(delteNode.left == null && delteNode.right == null){
            if(delteNode.parent != null){
                if(delteNode.parent != null){
                    if(delteNode.parent.data.compareTo(data) < 0){
                        delteNode.right = null;
                    } else {
                        delteNode.left = null;
                    }
                }
                delteNode.parent = null;
            }
        }

        //有一个子节点
        if(delteNode.left !=null & delteNode.right == null ||
           delteNode.right != null & delteNode.left == null){
            moveSubNode = null;
            if(delteNode.left != null) {
                moveSubNode = delteNode.left;
            } else {
                moveSubNode = delteNode.right;
            }
            if(delteNode.parent != null){
                if(delteNode.parent.data.compareTo(data) <= 0){
                    delteNode.parent.right = moveSubNode;
                } else {
                    delteNode.parent.left = moveSubNode;
                }
            }
            moveSubNode.parent = delteNode.parent;

        }

        //有两个子节点, 要找到右子树中的最小节点
        if(delteNode.left != null && delteNode.right != null){
            moveSubNode = delteNode.right;
            while (moveSubNode.left != null){
                moveSubNode = moveSubNode.left;
            }

            moveSubNode.parent = delteNode.parent;
            moveSubNode.left = delteNode.left;
            if(delteNode.right != moveSubNode){
                 moveSubNode.right = delteNode.right;
            }
            if(delteNode.parent != null){
                if(delteNode.parent.data.compareTo(data) <= 0){
                    delteNode.parent.right = moveSubNode;
                }else {
                    delteNode.parent.left = moveSubNode;
                }
            }
        }

        return moveSubNode;
    }


    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTreeImpl<>();
        binaryTree.add(2);
        binaryTree.add(5);
        binaryTree.add(9);
        binaryTree.add(1);
        binaryTree.add(6);

        System.out.println("二叉树的节点个数：" + binaryTree.size());

        System.out.println("书中的节点有：");
        for (Object obj : binaryTree.toArray()) {
            System.out.println(obj + "、");
        }

        System.out.println("[8是否存在]：" + binaryTree.contains(8));
        System.out.println("[9是否存在]：" + binaryTree.contains(9));


        int numbers[] = new int[]{80, 50, 90, 55, 30, 60, 10, 85, 95};
        BinaryTree<Integer> remove = new BinaryTreeImpl<>();
        for(int num: numbers){
            remove.add(num);
        }
        System.out.println("删除前的数据：" + Arrays.toString(remove.toArray()));
        remove.remove(90);
        System.out.println("删除后的数据：" + Arrays.toString(remove.toArray()));
    }
}
