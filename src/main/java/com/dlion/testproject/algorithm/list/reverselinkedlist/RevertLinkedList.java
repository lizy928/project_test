package com.dlion.testproject.algorithm.list.reverselinkedlist;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author lzy
 * @date 2021/10/20
 */
public class RevertLinkedList {

    public static void main(String[] args){
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);

        Node newNode = revertLinkedList(node);
        while (newNode != null){
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

    // 请按你的实际需求修改参数
    public static Node revertLinkedList(Node head) {
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        return prev;
    }
}


class Node {
    public int val;
    public Node next;
    public Node(int val) { this.val = val; }
}

