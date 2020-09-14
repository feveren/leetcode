package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 * 输入: 1, 2, 3, 3, 4, 4, 5
 * 输出: 1, 2, 5
 *
 * 示例 2:
 * 输入: 1, 1, 1, 2, 3
 * 输出: 2, 3
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class _082_deleteDuplicates {
    public static void main(String[] args) {
        _082_deleteDuplicates helper = new _082_deleteDuplicates();
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 3, 7, 4, 4, 5});
        node = helper.deleteDuplicates(node);
        NodeUtils.println(node);
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            if (slow.next.val != fast.next.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                while (slow.next.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }
}
