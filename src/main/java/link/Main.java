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
        ListNode build = ListNode.build(1, 2);
        System.out.println(removeNthFromEnd(build, 2));
        ListNode build1 = ListNode.build(3);
        ListNode build2 = ListNode.build(2, 3);
        System.out.println(getIntersectionNode(build1, build2));

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

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     * 示例1
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Intersected at '8'
     * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     * 示例2
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Intersected at '2'
     * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
     * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     * 提示：
     * listA 中节点数目为 m
     * listB 中节点数目为 n
     * 0 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
     * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
     * 思路：求出两链表长度，然后尾部对齐。双指针向后循环遍历，遇到地址相同则找到交点。
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //计算两链表长度
        int lenA = 1;
        ListNode tempA = headA;
        while (tempA.next != null) {
            tempA = tempA.next;
            lenA++;
        }
        int lenB = 1;
        ListNode tempB = headB;
        while (tempB.next != null) {
            tempB = tempB.next;
            lenB++;
        }
        if (lenA == lenB && lenA == 1 && headA.equals(headB)) {
            return headA;
        }
        //极端长度差值，对齐指针
        ListNode curA = headA;
        ListNode curB = headB;
        if (lenA > lenB) {
            int diffStep = lenA - lenB;
            for (int i = 0; i < diffStep; i++) {
                curA = curA.next;
            }
        } else if (lenA < lenB) {
            int diffStep = lenB - lenA;
            for (int i = 0; i < diffStep; i++) {
                curB = curB.next;
            }
        }
        //循环对比，找交点。
        while (curA != null && curB != null) {
            if (curA.equals(curB)) {
                return curA;
            } else {
                curA = curA.next;
                curB = curB.next;
            }
        }
        return null;
    }

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     * 思路：
     * 判断是否有环：快慢指针，如果快慢指针相遇，则说明有环。
     * 找到交点（推导过程省略）：从头结点出发一个指针，从相遇节点 也出发一个指针，这两个指针每次只走一个节点， 那么当这两个指针相遇的时候就是 环形入口的节点。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        //快慢指针，快指针step=2，慢指针step=1，如果相遇，则说明有环。
        while (fast != null) {
            slow = slow.next;
            //不是环链，退出循环
            if (fast.next == null) {
                break;
            }
            fast = fast.next.next;
            //是环链，退出循环
            if (slow.equals(fast)) {
                break;
            }
        }
        if (fast.next == null) {
            return null;
        }
        //按照公式推导，从相遇点step=1和起点step=1出发的两个指针，相遇的地方就是环的起点。
        ListNode indexHead = head;
        ListNode indexComeAcross = slow;
        while (!indexHead.equals(indexComeAcross)) {
            indexHead = indexHead.next;
            indexComeAcross = indexComeAcross.next;
        }
        return indexHead;
    }
}