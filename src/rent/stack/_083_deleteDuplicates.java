package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _083_deleteDuplicates {
    public static void main(String[] args) {
        _083_deleteDuplicates helper = new _083_deleteDuplicates();
        ListNode node = NodeUtils.buildStack(new int[]{1, 1, 1, 3});
        node = helper.deleteDuplicates2(node);
        NodeUtils.println(node);
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null) {
            // 将相同的数字全部删除完
            while (node.next != null && node.next.val == node.val) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                fast = fast.next;
                slow.next = fast;
            }
        }
        return head;
    }
}
