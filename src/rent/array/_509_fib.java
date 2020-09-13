package rent.array;

/**
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 * 示例 2：
 *
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
 * 示例 3：
 *
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 *  
 *
 * 提示：
 *
 * 0 ≤ N ≤ 30
 *
 * https://leetcode-cn.com/problems/fibonacci-number
 */
public class _509_fib {
    public static void main(String[] args) {
        test(10);
    }

    private static void test(int n) {
        int result;
        result = method1(n);
        System.out.println(String.format("method1: F(%d) = %d", n, result));

        result = method2(n);
        System.out.println(String.format("method2: F(%d) = %d", n, result));

        result = method3(n);
        System.out.println(String.format("method3: F(%d) = %d", n, result));

        result = method4(n);
        System.out.println(String.format("method4: F(%d) = %d", n, result));
    }

    private static int method1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return method1(n - 1) + method1(n - 2);
    }

    private static int method2(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] memo = new int[n];
        return method2Run(n, memo);
    }

    private static int method2Run(int n, int[] memo) {
        int memoIndex = n - 1;
        if (memo[memoIndex] > 0) {
            return memo[memoIndex];
        }
        if (n == 1) {
            return 1;
        }
        int fn = method1(n - 1) + method1(n - 2);
        memo[memoIndex] = fn;
        return fn;
    }

    private static int method3(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    private static int method4(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev = 1, current = 1, next = 0;
        for (int i = 2; i < n; i++) {
            next = prev + current;
            prev = current;
            current = next;
        }
        return current;
    }
}
