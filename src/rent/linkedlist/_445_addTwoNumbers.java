package rent.linkedlist;

import rent.model.ListNode;
import rent.utils.NodeUtils;

import java.util.LinkedList;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 */
public class _445_addTwoNumbers {
    public static void main(String[] args) {
        _445_addTwoNumbers helper = new _445_addTwoNumbers();
        NodeUtils.println(helper.addTwoNumbers(
                NodeUtils.buildStack(new int[]{1, 2, 2, 1}),
                NodeUtils.buildStack(new int[]{2, 8, 4})));
        NodeUtils.println(helper.addTwoNumbers(
                NodeUtils.buildStack(new int[]{5}),
                NodeUtils.buildStack(new int[]{5})));
    }

    public ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        while (node1 != null) {
            stack1.push(node1.val);
            node1 = node1.next;
        }
        while (node2 != null) {
            stack2.push(node2.val);
            node2 = node2.next;
        }
        ListNode prev = null;
        int added = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || added > 0) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop();
            int num2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = num1 + num2 + added;
            added = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = prev;
            prev = node;
        }
        return prev;
    }
}
