package rent.dynamic;

/**
 * 0-1 背包问题
 * 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两
 * 个属性。其中第 i 个物品的重量为 wt[i] ，价值为 val[i] ，现在让你⽤
 * 这个背包装物品，最多能装的价值是多少？
 *
 * 举个简单的例⼦，输⼊如下：
 * N = 3, W = 4
 * wt = [2, 1, 3]
 * val = [4, 2, 3]
 * 算法返回 6，选择前两件物品装进背包，总重量 3 小于 W ，可以获得最⼤
 * 价值 6。
 */
public class Bag_0_1 {
    public static void main(String[] args) {
        System.out.println(pick2(3, 4, new int[] {2, 1, 3}, new int[] {4, 2, 3}));
    }

    private static int pick(int maxSize, int maxWeight, int[] weight, int[] values) {
        int[][] dp = new int[maxSize + 1][maxWeight + 1];
        for (int i = 1; i <= maxSize; i++) {
            for (int w = 1; w <= maxWeight; w++) {
                if (w - weight[i - 1] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    int v = dp[i - 1][w - weight[i - 1]] + values[i - 1];
                    dp[i][w] = Math.max(v, dp[i - 1][w]);
                }
            }
        }
        return dp[maxSize][maxWeight];
    }

    private static int pick2(int maxSize, int maxWeight, int[] weight, int[] values) {
        int[] dp = new int[maxWeight + 1];
        for (int i = 0; i < maxSize; i++) {
            for (int w = maxWeight; w >= weight[i]; w--) {
                int v = dp[w - weight[i]] + values[i];
                dp[w] = Math.max(v, dp[w]);
            }
        }
        return dp[maxWeight];
    }
}
