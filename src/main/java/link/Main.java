package main.java.link;

/**
 * 链表相关的算法题
 */
public class Main {

    public static void main(String[] args) {
        ListNode head = ListNode.build(1, 2, 6, 3, 4, 5, 6);
        System.out.println(removeElements(head, 6));
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
}
