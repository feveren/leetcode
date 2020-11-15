package rent.order;

import rent.model.ListNode;
import rent.utils.NodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class Order06_reversePrint {
    public static void main(String[] args) {
        Order06_reversePrint helper = new Order06_reversePrint();
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3});
        int[] result = helper.reversePrint(node);
        System.out.println(Arrays.toString(result));
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        int[] result = new int[len];
        node = head;
        while (node != null) {
            result[len - 1] = node.val;
            node = node.next;
            len--;
        }
        return result;
    }

    public int[] reversePrint2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        reversePrint(head, list);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void reversePrint(ListNode head, List<Integer> result) {
        if (head == null) {
            return;
        }
        reversePrint(head.next, result);
        result.add(head.val);
    }
}
