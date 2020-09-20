package rent.utils;

import rent.model.ListNode;

public class NodeUtils {
    public static void println(ListNode node) {
        ListNode n = node;
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println();
    }

    public static ListNode buildStack(int[] array) {
        ListNode head = null;
        ListNode node = null;
        for (int i : array) {
            if (head == null) {
                head = new ListNode(i);
                node = head;
            } else {
                node.next = new ListNode(i);
                node = node.next;
            }
        }
        return head;
    }

    public static ListNode findNode(ListNode node, int val) {
        while (node != null) {
            if (node.val == val) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public static ListNode append(ListNode node1, ListNode node2) {
        ListNode head = node1;
        while (node1.next != null) {
            node1 = node1.next;
        }
        node1.next = node2;
        return head;
    }
}
