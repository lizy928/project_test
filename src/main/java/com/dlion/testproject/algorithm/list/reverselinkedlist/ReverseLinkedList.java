package com.dlion.testproject.algorithm.list.reverselinkedlist;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/solution/yi-bu-yi-bu-jiao-ni-ru-he-yong-di-gui-si-67c3/
 * @author lzy
 * @date 2021/10/21
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);

        Node newNode = reverse(node);
        while (newNode != null){
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

    public static Node reverse(Node head){
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

