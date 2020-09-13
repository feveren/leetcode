package rent.dynamic;

/**
 * int[][][] dp = new int[days][time][state]
 * days: 天数；time：买卖次数；state：当前是否持有
 * dp用来表示当前状态下的收益
 *
 * 前是0，也就是说昨天卖掉了，或者没有操作
 * dp[i][time][0] = Math.max(dp[i - 1][time][1] + prices[i], dp[i - 1][time][0]);
 * 当前是1，也就是说昨天买入，或者没有操作，买入时time - 1
 * dp[i][time][1] = Math.max(dp[i - 1][time - 1][0] - prices[i], dp[i - 1][time][1]);
 *
 * base case: i从0开始，time从1开始
 * i = -1
 * dp[-1][time][0] = 0 还没开始交易，未持有股票，收益为0
 * dp[-1][time][1] 无效 还没开始交易，不会持有股票，无效状态
 * time = 0
 * dp[i][0][0] = 0 交易次数为0，收益为0
 * dp[i][0][1] 无效 交易次数为0，不能发生交易，不能持有股票，无效状态
 */
public class _maxProfit {
    public static void main(String[] args) {
        System.out.println(once(new int[] {7,1,5,3,6,4}));
        System.out.println(once(new int[] {7,6,4,3,1}));
    }

    /**
     * 最多交易一次，time=1
     * dp[i][1][0] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][1][0]);
     * dp[i][1][1] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][1]);
     * 根据base case，dp[i - 1][0][0] = 0，其他皆为time=1，time对状态已经没有影响了，可以省略，即为
     * dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
     * dp[i][1] = Math.max(- prices[i], dp[i - 1][1]);
     */
    private static int once(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];

        for (int i = 0; i < len; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(- prices[i], dp[i - 1][1]);
        }
        return dp[len - 1][0];
    }

    /**
     * dp只和dp[i - 1]有关，可以将空间复杂度减小至O(1)
     */
    private static int once2(int[] prices) {
        int len = prices.length;
        int dpi0 = 0;
        int dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            dpi0 = Math.max(dpi1 + prices[i], dpi0);
            dpi1 = Math.max(- prices[i], dpi1);
        }
        return dpi0;
    }

    /**
     * 可以交易无限次，time无限大，所以time和time - 1都不会对状态产生影响，可以省略
     * dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0])
     * dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1])
     */
    private static int infinite(int[] prices) {
        int len = prices.length;
        int dpi0 = 0;
        int dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            dpi0 = Math.max(dpi1 + prices[i], dpi0);
            dpi1 = Math.max(dpi0 - prices[i], dpi1);
        }
        return dpi0;
    }

    /**
     * 买入后需要间隔一天再进行买入，买入时改为i - 1即可
     * dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0])
     * dp[i][1] = Math.max(dp[i - 2][0] - prices[i], dp[i - 1][1])
     */
    private static int coolDown(int[] prices) {
        int len = prices.length;
        int dpi0 = 0;
        int dpi1 = Integer.MIN_VALUE;
        int dpPre0 = 0;
        for (int i = 0; i < len; i++) {
            int temp = dpi0;
            dpi0 = Math.max(dpi1 + prices[i], dpi0);
            dpi1 = Math.max(dpPre0 - prices[i], dpi1);
            dpPre0 = temp;
        }
        return dpi0;
    }

    /**
     * 买入时需要交手续费，买入时从利润中减去fee即可
     * dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0])
     * dp[i][1] = Math.max(dp[i - 1][0] - prices[i] - fee, dp[i - 1][1])
     */
    private static int withFee(int[] prices, int fee) {
        int len = prices.length;
        int dpi0 = 0;
        int dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            dpi0 = Math.max(dpi1 + prices[i], dpi0);
            dpi1 = Math.max(dpi0 - prices[i] - fee, dpi1);
        }
        return dpi0;
    }

    /**
     * dp[i][k][0] = Math.max(dp[i - 1][k][1] + prices[i], dp[i - 1][k][0])
     * dp[i][k][1] = Math.max(dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1])
     */
    private static int limit(int[] prices, int k) {
        int len = prices.length;
        // 最多交易len/2次，如果大于len/2，等同于无限次
        if (k > len / 2) {
            return infinite(prices);
        }
        int[][][] dp = new int[len][k + 1][2];
        for (int i = 0; i < len; i++) {
            for (int j = k; j > 0; j--) {
                if (i - 1 < 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }
        return dp[len - 1][k][0];
    }
}
