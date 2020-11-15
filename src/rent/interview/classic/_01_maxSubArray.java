package rent.interview.classic;

/**
 * 最大子序和
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * https://leetcode-cn.com/leetbook/read/2020-top-interview-questions/xo341d/
 */
public class _01_maxSubArray {
    public static void main(String[] args) {
        _01_maxSubArray helper = new _01_maxSubArray();
        System.out.println(helper.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ret = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ret = Math.max(ret, sum);
        }
        return ret;
    }

    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
