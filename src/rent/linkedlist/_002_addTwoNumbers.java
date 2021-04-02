package rent.linkedlist;

import rent.model.ListNode;
import rent.utils.NodeUtils;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class _002_addTwoNumbers {
    public static void main(String[] args) {
        _002_addTwoNumbers helper = new _002_addTwoNumbers();
        NodeUtils.println(helper.addTwoNumbers(
                NodeUtils.buildStack(new int[]{1, 2, 2, 1}),
                NodeUtils.buildStack(new int[]{2, 8, 4})));
        NodeUtils.println(helper.addTwoNumbers(
                NodeUtils.buildStack(new int[]{5}),
                NodeUtils.buildStack(new int[]{5})));
    }

    public ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        int carry = 0;
        while (node1 != null || node2 != null || carry > 0) {
            int num1 = node1 == null ? 0 : node1.val;
            int num2 = node2 == null ? 0 : node2.val;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            node.next = new ListNode(sum % 10);
            node = node.next;
            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        return dummy.next;
    }
}
