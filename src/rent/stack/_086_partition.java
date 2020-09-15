package rent.stack;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 86. 分隔链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * https://leetcode-cn.com/problems/partition-list/
 */
public class _086_partition {
    public static void main(String[] args) {
        _086_partition helper = new _086_partition();
        ListNode node = NodeUtils.buildStack(new int[]{1, 4, 3, 2, 5, 2});
        node = helper.partition(node, 3);
        NodeUtils.println(node);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0);
        ListNode less = lessHead;
        ListNode greaterHead = new ListNode(0);
        ListNode greater = greaterHead;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        greater.next = null;
        less.next = greaterHead.next;
        return lessHead.next;
    }
}
