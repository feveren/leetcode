package rent.dynamic.backpack;

/**
 * 125. 背包问题 II
 * 描述
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
 * 问最多能装入背包的总价值是多大?
 *
 * A[i], V[i], n, m 均为整数
 * 你不能将物品进行切分
 * 你所挑选的要装入背包的物品的总大小不能超过 m
 * 每个物品只能取一次
 *
 * 样例
 * 样例 1:
 * 输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
 * 输出: 9
 * 解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
 *
 * 样例 2:
 * 输入: m = 10, A = [2, 3, 8], V = [2, 5, 8]
 * 输出: 10
 * 解释: 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
 * 挑战
 * O(nm) 空间复杂度可以通过, 不过你可以尝试 O(m) 空间复杂度吗?
 *
 * https://www.lintcode.com/problem/backpack-ii
 */
public class _0or1backPack {
    public static void main(String[] args) {
        _0or1backPack helper = new _0or1backPack();
        System.out.println(helper.backPack(10, new int[] {2, 3, 5, 7}, new int[] {1, 5, 2, 4}));
        System.out.println(helper.backPack(10, new int[] {2, 3, 8}, new int[] {2, 5, 8}));
    }

    public int backPack(int m, int[] A, int[] V) {
        if (m == 0 || A == null || A.length == 0 || V == null || V.length == 0) {
            return 0;
        }
        int[][] dp = new int[A.length + 1][m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (j >= A[i - 1]) {
                    dp[i][j] = Math.max(
                            V[i - 1] + dp[i - 1][j - A[i - 1]], // 取
                            dp[i - 1][j]                    // 不取
                    );
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[A.length][m];
    }
}
