package rent.dynamic;

import rent.model.TreeNode;
import rent.utils.MathUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class review_1 {
    public static void main(String[] args) {
//        for (int i = 1; i <= 10; i++) {
//            System.out.print(i + ": " + fib(i) + "  ");
//        }
//        System.out.println();
//        System.out.println(coinChange(new int[] {1, 2, 5}, 9));
//        System.out.println(bag01(new int[] {2, 1, 3}, new int[] {4, 2, 3}, 3, 4));
//        System.out.println(canPartition(new int[] {1, 5, 13, 7}));
//        System.out.println(change(5, new int[] {1, 2, 5}));
//        System.out.println(multi(new int[] {1, 2, 5}, new int[] {4, 1, 3}, 9));
//        System.out.println(rob(new int[] {1,2,3,1}));
//        System.out.println(rob(new int[] {2,7,9,3,1}));
        System.out.println(eraseOverlapIntervals(new int[][] {{1,2}, {2,3}, {3,4}, {1,3}}));
        System.out.println(eraseOverlapIntervals(new int[][] {{1,2}, {2,3}}));
        System.out.println(eraseOverlapIntervals(new int[][] {{1,2}, {1,2}, {1,2}}));
    }

    /**
     * dp(n) = dp(n - 1) + dp(n - 2)
     * base case: dp(1) = 1; dp(2) = 1
     */
    private static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int pre1;
        int pre2;
        pre1 = pre2 = 1;
        int current = 0;
        for (int i = 3; i <= n; i++) {
            current = pre1 + pre2;
            pre2 = pre1;
            pre1 = current;
        }
        return current;
//        int[] dp = new int[n + 1];
//        dp[1] = dp[2] = 1;
//        for (int i = 3; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
    }

    /**
     * 完全背包
     * 物品的数量无限
     * dp[i][a] = Math.min(
     *      dp[i][a - coins[i - 1]] + 1,
     *      dp[i - 1][a]
     * )
     *
     * 凑零钱1
     * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck ，每种硬
     * 币的数量无限，再给一个总金额 amount ，问你最少需要几枚硬币凑出这个
     * 金额，如果不可能凑出，算法返回 -1
     */
    static int coinChange(int[] coins, int amount) {
        int size = coins.length;
//        int[][] dp = new int[size + 1][amount + 1];
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                dp[i][j] = j == 0 ? 0 : amount + 1;
//            }
//        }
//        for (int i = 1; i <= size; i++) {
//            for (int j = 1; j <= amount; j++) {
//                int delta = j - coins[i - 1];
//                if (delta >= 0) {
//                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][delta] + 1);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }
//        return dp[size][amount] == amount + 1 ? -1 : dp[size][amount];

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int i = 0; i < size; i++) {
                int delta = a - coins[i];
                if (delta < 0) {
                    continue;
                }
                dp[a] = Math.min(dp[delta] + 1, dp[a]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 完全背包
     * 凑零钱2
     */
    static int change(int amount, int[] coins) {
        int len = coins.length;
//        int[][] dp = new int[len + 1][amount + 1];
//        for (int i = 0; i < dp.length; i++) {
//            dp[i][0] = 1;
//        }
//        for (int i = 1; i <= len; i++) {
//            for (int a = 1; a <= amount; a++) {
//                int delta = a - coins[i - 1];
//                if (delta >= 0) {
//                    dp[i][a] = dp[i - 1][a] + dp[i][delta];
//                } else {
//                    dp[i][a] = dp[i - 1][a];
//                }
//            }
//        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < len; i++) {
            for (int a = 1; a <= amount; a++) {
                int delta = a - coins[i];
                if (delta >= 0) {
                    dp[a] = dp[a] + dp[delta];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 01背包
     * 物品数量有限，取或者不取
     * dp[i][w] = Math.max(
     *      dp[i - 1][w],
     *      dp[i - 1][w - weight[i - 1]] + values[i - 1]
     * );
     */
    static int bag01(int[] weight, int[] values, int size, int maxWeight) {
        if (maxWeight <= 0 || size <= 0) {
            return 0;
        }

//        int[][] dp = new int[size + 1][maxWeight + 1];
//        for (int i = 1; i <= size; i++) {
//            for (int w = 1; w <= maxWeight; w++) {
//                int delta = w - weight[i - 1];
//                if (delta < 0) {
//                    dp[i][w] = dp[i - 1][w];
//                } else {
//                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][delta] + values[i - 1]);
//                }
//            }
//        }
//        return dp[size][maxWeight];

        int[] dp = new int[maxWeight + 1];
        for (int i = 0; i < size; i++) {
            for (int j = maxWeight; j >= weight[i]; j--) {
                int delta = j - weight[i];
                dp[j] = Math.max(dp[j], dp[delta] + values[i]);
            }
        }
        return dp[maxWeight];
    }

    /**
     * 子集背包
     */
    static boolean canPartition(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int len = arr.length;
        int max = sum / 2;
//        boolean[][] dp = new boolean[len + 1][max + 1];
//        for (int i = 1; i <= len; i++) {
//            for (int j = 1; j <= max; j++) {
//                int delta = j - arr[i - 1];
//                if (delta > 0) {
//                    dp[i][j] = dp[i - 1][j] || dp[i - 1][delta];
//                } else if (delta < 0) {
//                    dp[i][j] = dp[i - 1][j];
//                } else if (delta == 0) {
//                    dp[i][j] = true;
//                }
//            }
//        }
//        return dp[len][max];

        boolean[] dp = new boolean[max + 1];
        for (int i = 0; i < len; i++) {
            for (int j = max; j >= arr[i]; j--) {
                int delta = j - arr[i];
                if (delta == 0) {
                    dp[j] = true;
                } else {
                    dp[j] = dp[j] || dp[delta];
                }
            }
        }
        return dp[max];
    }

    static int multi(int[] coins, int[] sizes, int amount) {
        int len = coins.length;
//        int[][] dp = new int[len + 1][amount + 1];
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                dp[i][j] = j == 0 ? 0 : amount + 1;
//            }
//        }
//        for (int i = 1; i <= len; i++) {
//            for (int j = 1; j <= amount; j++) {
//                for (int k = 1; k <= sizes[i - 1]; k++) {
//                    int delta = j - k * coins[i - 1];
//                    if (delta >= 0) {
//                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][delta] + k);
//                    }
//                    // 这里不能加else，会导致dp[i][j]的被覆盖，因为dp[i][j]会循环k次并进行赋值
//                }
//            }
//        }
//        return dp[len][amount] == amount + 1 ? -1 : dp[len][amount];

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = amount; j >= 1; j--) {
                for (int k = 1; k <= sizes[i - 1]; k++) {
                    int delta = j - k * coins[i - 1];
                    
                    if (delta < 0) {
                        break;
                    }
                    dp[j] = Math.min(dp[j], dp[delta] + k);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
     * dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     *
     *
     */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            if (i - 1 < 0) {
                dp[i][0] = 0;
                dp[i][1] = Integer.MIN_VALUE;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public static int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            if (i - 1 < 0) {
                dp[i][0] = 0;
                dp[i][1] = Integer.MIN_VALUE;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public static int maxProfit3(int[] prices) {
        int k = 2;
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2];
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                if (i - 1 < 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }

        }
        return dp[len - 1][k][0];
    }

    /**
     * 最长公共子序列
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        if (len2 == 0 || len1 == 0) {
            return 0;
        }
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 最长上升子序列
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(dp[i], max);
        }
        return dp[len];
    }

    /**
     * 最长回文子串
     * 示例 1:
     * 输入:
     * "bbbab"
     * 输出:
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     * "cbbd"
     * 输出:
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *  
     * 示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = MathUtils.max(
                            dp[i - 1][j - 1] + 1,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1
                    );
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
//        int[] dp = new int[len + 1];
//        dp[1] = nums[0];
//        for (int i = 2; i <= len; i++) {
//            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
//        }

        int dpi1 = nums[0];
        int dpi2 = 0;
        for (int i = 2; i <= len; i++) {
            int tmp = dpi1;
            dpi1 = Math.max(nums[i - 1] + dpi2, dpi1);
            dpi2 = tmp;
        }
        return dpi1;
    }

    private static Map<TreeNode, Integer> rob3Memo = new HashMap<>();
    public static int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (rob3Memo.containsKey(root)) {
            return rob3Memo.get(root);
        }
        int rob = root.val
                + (root.left != null ? root.left.left.val + root.left.right.val : 0)
                + (root.right != null ? root.right.left.val + root.right.right.val : 0);
        int notRob = rob3(root.left) + rob3(root.right);
        int res = Math.max(rob, notRob);
        rob3Memo.put(root, res);
        return res;
    }

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * 注意:
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     *
     * 示例 1:
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     *
     * 示例 2:
     * 输入: [ [1,2], [1,2], [1,2] ]
     * 输出: 2
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     *
     * 示例 3:
     * 输入: [ [1,2], [2,3] ]
     * 输出: 0
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len <= 1) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                count++;
                end = intervals[i][1];
            }
        }
        return count;
    }

    /**
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，
     * 因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
     * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
     * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。
     * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * Example:
     * 输入:
     * [[10,16], [2,8], [1,6], [7,12]]
     * 输出:
     * 2
     * 解释:
     * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
     */
    public int findMinArrowShots(int[][] points) {
        int len = points.length;
        if (len <= 1) {
            return len;
        }
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);
        int end = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (end < points[i][0]) {
                count++;
                end = points[i][1];
            }
        }
        return count;
    }

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
     */
    public int superEggDrop(int K, int N) {
        return 0;
    }
}

