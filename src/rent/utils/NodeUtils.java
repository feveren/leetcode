package rent.utils;

import rent.model.ListNode;

public class NodeUtils {
    public static void println(ListNode node) {
        System.out.println(toString(node));
    }

    public static String toString(ListNode node) {
        StringBuilder builder = new StringBuilder();
        ListNode n = node;
        while (n != null) {
            builder.append(n.val + " ");
            n = n.next;
        }
        builder.append("\n");
        return builder.toString();
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
