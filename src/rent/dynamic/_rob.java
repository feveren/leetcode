package rent.dynamic;

import rent.model.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _rob {
    public static void main(String[] args) {
        System.out.println(rob2(new int[] {2, 7, 9, 3, 1}));
        System.out.println(rob2(new int[] {1, 2, 3, 1}));
    }

    private static int rob(int[] houses) {
        int[] memo = new int[houses.length];
        Arrays.fill(memo, -1);
        return rob(houses, 0, memo);
    }

    private static int rob(int[] houses, int start, int[] memo) {
        int len = houses.length;
        if (start >= len) {
            return 0;
        }
        if (memo[start] >= 0) {
            return memo[start];
        }
        int res = Math.max(
                rob(houses, start + 1, memo),
                houses[start] + rob(houses, start + 2, memo)
        );
        memo[start] = res;
        return res;
    }

    private static int rob2(int[] houses) {
        int len = houses.length;
//        int[] dp = new int[len + 2];
//        for (int i = len - 1; i >= 0; i--) {
//            dp[i] = Math.max(dp[i + 1], dp[i + 2] + houses[i]);
//        }
//        return dp[0];

        int dpi = 0, dpi1 = 0, dpi2 = 0;
        for (int i = len - 1; i >= 0; i--) {
            dpi = Math.max(dpi1, dpi2 + houses[i]);
            dpi2 = dpi1;
            dpi1 = dpi;
        }
        return dpi1;
    }

    private static int robCycle(int[] houses) {
        int len = houses.length;
        return Math.max(
                robRange(houses, 0, len - 2),
                robRange(houses, 1, len - 1)
        );
    }

    private static int robRange(int[] houses, int start, int end) {
        int dpi = 0, dpi1 = 0, dpi2 = 0;
        for (int i = end; i >= start; i--) {
            dpi = Math.max(dpi1, dpi2 + houses[i]);
            dpi2 = dpi1;
            dpi1 = dpi;
        }
        return dpi1;
    }

    private static final Map<TreeNode, Integer> memo = new HashMap<>();

    private static int robTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int value = memo.getOrDefault(root, -1);
        if (value >= 0) {
            return value;
        }
        int rob = root.val +
                (root.left == null ? 0 : robTree(root.left.left) + robTree(root.left.right)) +
                (root.right == null ? 0 : robTree(root.right.left) + robTree(root.right.right));
        int notRob = robTree(root.left) + robTree(root.right);
        int res = Math.max(rob, notRob);
        memo.put(root, res);
        return res;
    }

    /**
     * int[0]不抢root的收益；int[0]抢root的收益
     */
    private static int[] robTreeDp(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int[] left = robTreeDp(root.left);
        int[] right = robTreeDp(root.right);
        int rob = root.val + left[0] + right[0];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] {notRob, rob};
    }
}
