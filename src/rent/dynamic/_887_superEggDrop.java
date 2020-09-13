package rent.dynamic;

import java.util.Arrays;

/**
 * 887. 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 * 示例 1：
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 *
 * 示例 2：
 * 输入：K = 2, N = 6
 * 输出：3
 *
 * 示例 3：
 * 输入：K = 3, N = 14
 * 输出：4
 *
 * 提示：
 * 1 <= K <= 100
 * 1 <= N <= 10000
 *
 * https://leetcode-cn.com/problems/super-egg-drop/
 */
public class _887_superEggDrop {
    public static void main(String[] args) {
        System.out.println(superEggDrop(1, 2));
        System.out.println(superEggDrop(2, 6));
        System.out.println(superEggDrop(3, 14));
    }

    /**
     * dp[k][m]：k个鸡蛋，扔m次，可以扔n层
     */
    public static int superEggDrop(int K, int N) {
        if (K == 1) {
            return N;
        }
        if (N == 0 || K == 0) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int j = 1; j <= K; j++) {
                dp[j][m] = dp[j][m - 1] + dp[j - 1][m - 1] + 1;
            }
        }
        return m;
    }

    public static int superEggDrop2(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(memo[i], -1);
        }
        return binary(K, N, memo);
    }

    private static int binary(int K, int N, int[][] memo) {
        if (K == 1) {
            return N;
        }
        if (N == 0 || K == 0) {
            return 0;
        }
        if (memo[K][N] > 0) {
            return memo[K][N];
        }
        int result = Integer.MAX_VALUE;
        int left = 1;
        int right = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            int broken = superEggDrop(K - 1, mid - 1);
            int notBroken = superEggDrop(K, N - mid);
            if (broken > notBroken) {
                right = mid - 1;
                result = Math.min(result, broken + 1);
            } else {
                left = mid + 1;
                result = Math.min(result, notBroken + 1);
            }
            memo[K][N] = result;
        }
        return memo[K][N];
    }

    /**
     * dp[k][n]：k个鸡蛋，n层楼，扔几次
     */
    private static int recursion(int K, int N, int[][] memo) {
        if (K == 1) {
            return N;
        }
        if (N == 0 || K == 0) {
            return 0;
        }
        if (memo[K][N] > 0) {
            return memo[K][N];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int borken = superEggDrop(K - 1, i - 1);
            int notBroken = superEggDrop(K, N - i);
            result = Math.min(result,
                    Math.max(borken + 1, notBroken + 1)
            );
            memo[K][N] = result;
        }
        return memo[K][N];
    }
}
