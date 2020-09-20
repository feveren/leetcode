package rent.linkedlist;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class _025_reverseKGroup {
    public static void main(String[] args) {
        _025_reverseKGroup helper = new _025_reverseKGroup();
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        node = helper.reverseKGroup(node, 4);
        NodeUtils.println(node);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode pointer = head;
        while (pointer != null) {
            ListNode node = pointer;
            for (int i = 0; i < k - 1 && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }
            for (int i = 0; i < k - 1; i++) {
                ListNode next = pointer.next;
                pointer.next = next.next;
                next.next = guard.next;
                guard.next = next;
            }
            guard = pointer;
            pointer = pointer.next;
        }
        return dummy.next;
    }
}
