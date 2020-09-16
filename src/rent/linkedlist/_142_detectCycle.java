package rent.linkedlist;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class _142_detectCycle {
    public static void main(String[] args) {
        _142_detectCycle helper = new _142_detectCycle();
        ListNode node = NodeUtils.buildStack(new int[]{1});
//        ListNode n = NodeUtils.findNode(node, 3);
//        ListNode last = NodeUtils.findNode(node, 6);
//        last.next = n;
        System.out.println(helper.detectCycle(node));
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
