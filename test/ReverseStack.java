import org.junit.Test;
import rent.model.ListNode;
import rent.utils.NodeUtils;

public class ReverseStack {
    @Test
    public void reverseStackTest() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5, 6, 7});
//        node = reverseStack(node);
//        node = reverseBetween(node, 2, 4);
        node = reversePair(node);
        NodeUtils.println(node);
    }

    private ListNode reverseStack(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || n <= m) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode pointer = dummy.next;
        for (int i = 0; i < m - 1; i++) {
            guard = guard.next;
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

    public ListNode reversePair(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode pointer = dummy.next;
        while (pointer != null && pointer.next != null) {
            ListNode next = pointer.next;
            pointer.next = next.next;
            next.next = guard.next;
            guard.next = next;

            guard = pointer;
            pointer = pointer.next;
        }
        return dummy.next;
    }
}
