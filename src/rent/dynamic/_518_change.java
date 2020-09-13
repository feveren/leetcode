package rent.dynamic;

/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 *
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 *
 * https://leetcode-cn.com/problems/coin-change-2/
 */
public class _518_change {
    public static void main(String[] args) {
        System.out.println(change(5, new int[] {1, 2, 5}));
        System.out.println(change2(5, new int[] {1, 2, 5}));
    }

    private static int change(int amount, int[] coins) {
        if (amount <= 0) {
            return 0;
        }
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= len; i++) {
            for (int a = 1; a <= amount; a++) {
                if (a < coins[i - 1]) {
                    dp[i][a] = dp[i - 1][a];
                } else {
                    dp[i][a] = dp[i - 1][a] + dp[i][a - coins[i - 1]];
                }
            }
        }
        return dp[len][amount];
    }

    private static int change2(int amount, int[] coins) {
        if (amount <= 0) {
            return 0;
        }
        int len = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int a = 1; a <= amount; a++) {
                if (a >= coins[i - 1]) {
                    dp[a] = dp[a] + dp[a - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }
}
