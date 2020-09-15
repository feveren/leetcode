package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * https://leetcode-cn.com/problems/reorder-list/
 */
public class _143_reorderList {
    public static void main(String[] args) {
        _143_reorderList helper = new _143_reorderList();
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        helper.reorderList(node);
        NodeUtils.println(node);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode left = head;
        ListNode mid = findMiddle(left);
        ListNode right = mid.next;
        mid.next = null;
        right = reverse(right);

        ListNode curr = new ListNode(0);
        while (left != null && right != null) {
            curr.next = left;
            left = left.next;
            curr = curr.next;

            curr.next = right;
            right = right.next;
            curr = curr.next;
        }
        curr.next = left;
    }

    private ListNode reverse(ListNode head) {
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

    private ListNode findMiddle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
