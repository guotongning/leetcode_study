package main.java.link;

public class ListNode {
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

    public static ListNode build(int... nums) {
        ListNode head = new ListNode();
        head.val = nums[0];
        ListNode temp = head;
        for (int i = 1; i < nums.length; i++) {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        return head;
    }

    @Override
    public String toString() {
        ListNode head = this;
        StringBuilder sb = new StringBuilder();
        sb.append(this.val).append(" ");
        ListNode temp = head.next;
        while (temp != null) {
            sb.append(temp.val).append(" ");
            temp = temp.next;
        }
        return sb.toString().trim();
    }
}
