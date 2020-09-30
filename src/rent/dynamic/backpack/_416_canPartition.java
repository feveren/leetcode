package rent.dynamic.backpack;

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
        _416_canPartition helper = new _416_canPartition();
        System.out.println(helper.canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(helper.canPartition(new int[] {1, 2, 3, 5}));
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int k : nums) {
            sum += k;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        sum = sum >> 1;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        int len = nums.length;
        for (int j = 0; j < len; j++) {
            for (int i = sum; i >= nums[j]; i--) {
                if (dp[sum]) {
                    return true;
                }
                dp[i] = dp[i - nums[j]] || dp[i];
            }
        }
        return dp[sum];
    }

    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int k : nums) {
            sum += k;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        sum = sum >> 1;
        int len = nums.length;
        boolean[][] dp = new boolean[len + 1][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                int num = nums[i - 1];
                if (num > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - num] || dp[i - 1][j];
                }
            }
        }
        return dp[len][sum];
    }
}
