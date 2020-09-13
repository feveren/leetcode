package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class _092_reverseBetween {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        ListNode listNode = reverseBetween(head, 2, 4);
        ListNode listNode = reverseN(head, 3);
        NodeUtils.println(listNode);
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode pointer = head;
        for (int i = 0; i < m - 1 && pointer != null; i++) {
            guard = pointer;
            pointer = pointer.next;
        }
        for (int i = 0; i < n - m && pointer != null; i++) {
            ListNode next = pointer.next;
            pointer.next = next.next;
            next.next = guard.next;
            guard.next = next;
        }
        return dummy.next;
    }

    public static ListNode reverseN(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode guard = dummy;
        ListNode pointer = dummy.next;

        while (pointer != null && pointer.next != null) {
            for (int i = 0; i < n - 1; i++) {
                ListNode moved = pointer.next;
                if (moved != null) {
                    pointer.next = moved.next;
                    moved.next = guard.next;
                    guard.next = moved;
                }
            }

            guard = pointer;
            pointer = pointer.next;
        }
        return dummy.next;
    }

    public static ListNode reversePair(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode newHead = null;
        ListNode mid = null;
        int count = 1;
        while (curr != null) {
            if (count % 2 == 0) {
                ListNode next = curr.next;
                curr.next = prev;
                if (mid != null) {
                    mid.next = curr;
                }
                prev.next = next;
                mid = prev;
                prev = curr;
                curr = next;
                if (newHead == null) {
                    newHead = prev;
                }
            } else {
                prev = curr;
                curr = curr.next;
            }
            count++;
        }
        return newHead;
    }

    public static ListNode reverse(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        int count = 1;
        while (curr != null && count <= n) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        head.next = curr;
        return prev;
    }
}
