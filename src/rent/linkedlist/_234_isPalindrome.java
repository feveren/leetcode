package rent.linkedlist;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class _234_isPalindrome {
    public static void main(String[] args) {
        _234_isPalindrome helper = new _234_isPalindrome();
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 2, 1});
        System.out.println(helper.isPalindrome(node));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode left = head;
        ListNode mid = findMiddle(head);
        ListNode right = mid.next;
        mid.next = null;
        right = reverse(right);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return left == null || left.next == null;
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

    private ListNode reverse(ListNode node) {
        ListNode curr = node;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
