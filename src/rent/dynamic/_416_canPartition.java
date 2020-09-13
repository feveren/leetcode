package rent.dynamic;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */
public class _416_canPartition {
    public static void main(String[] args) {
        System.out.println(canPartition3(new int[] {1, 5, 11, 5}));
        System.out.println(canPartition3(new int[] {1, 2, 3, 5}));
    }

    private static boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        int size = nums.length;
        boolean[][] dp = new boolean[size + 1][sum + 1];
        for (int i = 1; i <= size; i++) {
            for (int w = 1; w <= sum; w++) {
                if (w - nums[i - 1] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else if (w - nums[i - 1] == 0) {
                    dp[i][w] = true;
                } else {
                    dp[i][w] = dp[i - 1][w] || dp[i - 1][w - nums[i - 1]];
                }
            }
        }
        for (int i = 0; i < dp[0].length; i++) {
            System.out.print(i + "     ");
        }
        System.out.println("");
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print((dp[i][j] ? dp[i][j] + " " : dp[i][j]) + " ");
            }
            System.out.println();
        }
        return dp[size][sum];
    }

    private static boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        int size = nums.length;
        boolean[] dp = new boolean[sum + 1];
        for (int i = 0; i < size; i++) {
            for (int w = sum; w >= nums[i]; w--) {
                if (w == nums[i]) {
                    dp[w] = true;
                } else {
                    dp[w] = dp[w] || dp[w - nums[i]];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + "     ");
        }
        System.out.println("");
        for (int i = 0; i < dp.length; i++) {
            System.out.print((dp[i] ? dp[i] + " " : dp[i]) + " ");
        }
        System.out.println();
        return dp[sum];
    }

    private static boolean canPartition3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        sum = sum / 2;
        Set<Integer> trace = new LinkedHashSet<>();
        return dfs(nums, 0, sum, trace);
    }

    private static boolean dfs(int[] nums, int total, int target, Set<Integer> trace) {
        if (total == target) {
            for (Integer integer : trace) {
                System.out.print(nums[integer] + " ");
            }
            System.out.println();
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (trace.contains(i)) {
                continue;
            }
            total += nums[i];
            trace.add(i);
            if (dfs(nums, total, target, trace)) {
                return true;
            }
            total -= nums[i];
            trace.remove(i);
        }
        return false;
    }
}
