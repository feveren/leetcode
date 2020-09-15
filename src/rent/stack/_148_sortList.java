package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 148. 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * https://leetcode-cn.com/problems/sort-list/
 */
public class _148_sortList {
    public static void main(String[] args) {
        _148_sortList helper = new _148_sortList();
        ListNode node = NodeUtils.buildStack(new int[]{6, 4, 6, 1, 3, 5});
        node = helper.sortList(node);
        NodeUtils.println(node);
    }

    public ListNode sortList(ListNode head) {
        return divideAndMerge(head);
    }

    private ListNode divideAndMerge(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMiddle(head);
        ListNode head2 = mid.next;
        // 断开两个链表的链接
        mid.next = null;
        ListNode node1 = divideAndMerge(head);
        ListNode node2 = divideAndMerge(head2);
        return merge(node1, node2);
    }

    /**
     * 找到中间节点，如果是偶数个，需要找左侧节点
     */
    private ListNode findMiddle(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode slow = node;
        // fast初始化为node.next，偶数个节点时，会找到左侧的节点
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
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
