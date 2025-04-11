package main.java.link;

/**
 * 链表相关的算法题
 */
public class Main {

    public static void main(String[] args) {
        ListNode head = ListNode.build(1, 2, 6, 3, 4, 5, 6);
        System.out.println(removeElements(head, 6));
        System.out.println(removeElements1(head, 6));
        ListNode newHead = reverseList(head);
        System.out.println(newHead);
        newHead = reverseListRecursion(newHead);
        System.out.println(newHead);
        newHead = swapPairs(newHead);
        System.out.println(newHead);
        ListNode build = ListNode.build(1,2);
        System.out.println(removeNthFromEnd(build, 2));

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        myLinkedList.get(4);
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);
    }

    /**
     * 题意：删除链表中等于给定值 val 的所有节点。
     * 示例 1： 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
     * 示例 2： 输入：head = [], val = 1 输出：[]
     * 示例 3： 输入：head = [7,7,7,7], val = 7 输出：[]
     */
    public static ListNode removeElements(ListNode head, int val) {
        //这里一定要用while，不然 7 7 7这种情况最后会剩下一个元素删不掉。
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * 虚拟头节点
     */
    public static ListNode removeElements1(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 双指针
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode pre = null;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 正向递归
     */
    public static ListNode reverseListRecursion(ListNode head) {
        return reverse(null, head);
    }

    private static ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode temp = null;
        temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

    /**
     * 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 输入：head = []
     * 输出：[]
     * 输入：head = [1]
     * 输出：[1]
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode temp;
        ListNode left;
        ListNode right;
        while (cur.next != null && cur.next.next != null) {
            temp = cur.next.next.next;
            left = cur.next;
            right = cur.next.next;
            //交换
            cur.next = right;
            right.next = left;
            left.next = temp;
            cur = left;
        }
        return dummy.next;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 示例 1:
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * 思路：快慢指针，两指针间隔n。
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode l = dummy;
        ListNode r = dummy;
        for (int i = 0; i < n; i++) {
            r = r.next;
        }
        if (r == null) {
            return null;
        }
        while (r.next != null) {
            l = l.next;
            r = r.next;
        }
        //删除
        if (l.next != null) {
            l.next = l.next.next;
        }
        return dummy.next;
    }
}