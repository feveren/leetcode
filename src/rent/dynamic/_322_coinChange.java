package rent.dynamic;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 * https://leetcode-cn.com/problems/coin-change
 */
public class _322_coinChange {
    public static void main(String[] args) {
//        test(new int[] { 1, 2, 5 }, 11);
        test(new int[] { 2 }, 3);
    }

    private static void test(int[] coins, int amount) {
        int result;
//        result = method1(coins, amount);
//        result = method2(coins, amount);
        result = method3(coins, amount);
        System.out.println(String.format("coins: %s, amount: %d, result: %d", Arrays.toString(coins), amount, result));
    }

    private static int method1(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = method1(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            count = Math.min(count, 1 + sub);
        }
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    /**
     * amount < 0: -1
     * amount = 0: 0
     * dp(amount) = 1 + dp(amount - coin)    coin in coins
     */
    private static int method2(int[] coins, int amount) {
        return method2Run(coins, amount, new int[amount]);
    }

    private static int method2Run(int[] coins, int amount, int[] memo) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int memoIndex = amount - 1;
        if (memo[memoIndex] != 0) {
            return memo[memoIndex];
        }
        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = method1(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            count = Math.min(count, 1 + sub);
        }
        memo[memoIndex] = count == Integer.MAX_VALUE ? -1 : count;
        return memo[memoIndex];
    }

    /**
     * {@link #method2(int[], int)}
     */
    private static int method3(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int count = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0 || dp[i - coin] == -1) {
                    continue;
                }
                count = Math.min(count, 1 + dp[i - coin]);
            }
            dp[i] = count == Integer.MAX_VALUE ? -1 : count;
        }
        return dp[amount];
    }
}
