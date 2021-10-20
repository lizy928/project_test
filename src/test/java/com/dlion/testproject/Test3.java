package com.dlion.testproject;

/**
 * @author lzy
 * @date 2021/10/20
 */
public class Test3 {

    public static Node neNode = null;

    public static void main(String[] args){
        Node node = new Node(3);
        node.next = new Node(1);
        node.next.next = new Node(6);

        revertLinkedList(node);
        System.out.print(neNode.val);

    }

    // 请按你的实际需求修改参数
    public static Node revertLinkedList(Node head) {
        if(head == null){
            return null;
        }
        if(head.next != null){
            Node temp = revertLinkedList(head.next);
            if(temp.next == null){
                neNode = new Node(temp.val);
            } else {
                neNode.next = new Node(temp.val);
                neNode = neNode.next;
            }
        }
        return head;
    }
}


class Node {
    public int val;
    public Node next;
    public Node(int val) { this.val = val; }
}
