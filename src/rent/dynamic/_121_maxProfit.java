package rent.dynamic;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class _121_maxProfit {
    public static void main(String[] args) {
        _121_maxProfit helper = new _121_maxProfit();
        System.out.println(helper.maxProfit2(new int[] {7,1,5,3,6,4}));
        System.out.println(helper.maxProfit2(new int[] {7,6,4,3,1}));
    }

    /**
     * 0表示当前没有持有股票，说明前一天进行了卖出，或者没有进行交易
     * dp[i][k][0] = Math.max(dp[i - 1][k][1] + prices[i], dp[i - 1][k][0])
     * 1表示当前持有股票，说明前一天进行了买入，或者没有进行交易
     * dp[i][k][1] = Math.max(dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1])
     * 该题目中k为1，而当k=0时，说明不允许进行交易，所以dp[i - 1][0][0] = 0
     * dp[i][1][0] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][1][0])
     * dp[i][1][1] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][1])
     *             = Math.max(0 - prices[i], dp[i - 1][1][1])
     * 题目中的k都为1，状态的变化与k无关，可以将k省略
     * dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0])
     * dp[i][1] = Math.max(- prices[i], dp[i - 1][1])
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < prices.length; i++) {
            if (i - 1 < 0) {
                dp[i][0] = 0;
                dp[i][1] = Integer.MIN_VALUE;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(- prices[i], dp[i - 1][1]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit2(int[] prices) {
        int dp0 = 0;
        int dp1 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp0 = Math.max(dp1 + price, dp0);
            dp1 = Math.max(-price, dp1);
        }
        return dp0;
    }
}
