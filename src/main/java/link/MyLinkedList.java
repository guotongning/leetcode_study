package main.java.link;

public class MyLinkedList {

    private ListNode head;
    private final ListNode dummy = new ListNode(0);
    private int size;


    public MyLinkedList() {

    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = dummy.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur == null ? -1 : cur.val;
    }

    public void addAtHead(int val) {
        if (head == null) {
            head = new ListNode(val);
            dummy.next = head;
        } else {
            ListNode oldHead = head;
            head = new ListNode(val, oldHead);
            dummy.next = head;
        }
        size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            head = new ListNode(val);
            dummy.next = head;
        } else {
            ListNode cur = dummy;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode(val);
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        ListNode cur = dummy;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode next = cur.next;
        cur.next = new ListNode(val, next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode cur = dummy;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        if (cur.next != null && cur.next.next != null) {
            cur.next = cur.next.next;
        } else {
            cur.next = null;
        }
        size--;
    }
}