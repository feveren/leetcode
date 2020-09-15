package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class _021_mergeTwoLists {
    public static void main(String[] args) {
        _021_mergeTwoLists helper = new _021_mergeTwoLists();
        ListNode l1 = NodeUtils.buildStack(new int[] {1, 2, 4});
        ListNode l2 = NodeUtils.buildStack(new int[] {1, 3, 4});
        ListNode node = helper.mergeTwoLists(l1, l2);
        NodeUtils.println(node);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return dummy.next;
    }
}
