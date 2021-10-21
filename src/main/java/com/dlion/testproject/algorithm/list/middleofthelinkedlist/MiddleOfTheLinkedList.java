package com.dlion.testproject.algorithm.list.middleofthelinkedlist;


/**
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        ListNode listNode = middleNode3(node);
        System.out.println(listNode.val);
    }

    /**
     * 计数法
     *
     * 单指针法
     * 我们可以对方法一进行空间优化，省去数组 A。
     *
     * 我们可以对链表进行两次遍历。第一次遍历时，我们统计链表中的元素个数 N；第二次遍历时，我们遍历到第 N/2 个元素（链表的首节点为第 0 个元素）时，将该元素返回即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/lian-biao-de-zhong-jian-jie-dian-by-leetcode-solut/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode cur = head;
        int num = 0;
        while (cur != null){
            cur = cur.next;
            num ++;
        }
        ListNode temp = head;
        int middle = num /2;
        for(int i=0;i<middle;i ++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 数组法
     * 数组
     * 思路和算法
     *
     * 链表的缺点在于不能通过下标访问对应的元素。因此我们可以考虑对链表进行遍历，同时将遍历到的元素依次放入数组 A 中。如果我们遍历到了 N 个元素，那么链表以及数组的长度也为 N，对应的中间节点即为 A[N/2]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/lian-biao-de-zhong-jian-jie-dian-by-leetcode-solut/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode2(ListNode head) {
        ListNode[] nodesArr = new ListNode[100];
        int t = 0;
        while (head != null){
            nodesArr[t++] = head;
            head = head.next;
        }
        return nodesArr[t/2];
    }

    /**
     * 双指针法、快慢指针
     *
     * 快慢指针法
     * 思路和算法
     *
     * 我们可以继续优化方法二，用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。那么当 fast 到达链表的末尾时，slow 必然位于中间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/lian-biao-de-zhong-jian-jie-dian-by-leetcode-solut/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode3(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}