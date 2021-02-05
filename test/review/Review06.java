package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.NodeUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class Review06 {
    @Test // TODO
    public void maxDepth() {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        TreeNode left = head.left;
        left.left = new TreeNode(15);

        System.out.println(maxDepth(head));
        System.out.println(minDepth(head));
    }

    private int maxDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int left = maxDepth(head.left);
        int right = maxDepth(head.right);
        return Math.max(left, right) + 1;
    }

    private int minDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int left = minDepth(head.left);
        int right = minDepth(head.right);
        if (left == 0 && right == 0) {
            return 1;
        }
        if (left == 0) {
            return right + 1;
        }
        if (right == 0) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    @Test
    public void deleteDuplicates() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 1, 1, 3});
        NodeUtils.println(deleteDuplicates(node));

        node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 3, 7, 4, 4, 5});
        NodeUtils.println(deleteDuplicates2(node));
    }

    private ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val == fast.val) {
                fast = fast.next;
                slow.next = fast;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return head;
    }

    private ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null && fast.next != null) {
            if (slow.next.val == fast.next.val) {
                while (fast.next != null && slow.next.val == fast.next.val) {
                    fast = fast.next;
                }
                fast = fast.next;
                slow.next = fast;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }

    @Test // TODO
    public void maxSlidingWindow() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] {1, -1}, 1)));
    }

    private int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int length = nums.length;
        int[] res = new int[length - k + 1];
        for (int i = 0; i < length; i++) {
            while (!queue.isEmpty() && nums[queue.peekFirst()] < nums[i]) {
                queue.removeFirst();
            }
            queue.addLast(i);
            if (i >= k - 1) {
                int index = i - k + 1;
                res[index] = nums[queue.peekFirst()];
                if (queue.peekFirst() == index) {
                    queue.removeFirst();
                }
            }
        }
        return res;
    }

    @Test // TODO
    public void minCut() {
//        System.out.println(minCut("aab"));
//        System.out.println(minCut("abac"));
//        System.out.println(minCut("abdc"));
//        System.out.println(minCut("abdd"));
        System.out.println(minCut("abbab"));
    }

    private int minCut(String str) {
        int len = str.length();
        boolean[][] memo = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    if (j - i <= 2) {
                        memo[i][j] = true;
                    } else {
                        memo[i][j] = memo[i + 1][j - 1];
                    }
                }
            }
        }
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (memo[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
            for (int j = 1; j < i; j++) {
                if (memo[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1] == len + 1 ? -1 : dp[len - 1];
    }
}
