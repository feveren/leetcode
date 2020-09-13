package rent.dynamic;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class _072_minDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));
    }

    /**
     * dp[i][j]：word1前i位，变换成word2的前j位，需要的最少步数
     * 选择：什么都不执行、增加、删除、修改
     * base case：dp[0][j] = j；dp[i][0] = i
     * min(dp[i - 1][j], // i删除，指针移动，j不动
     *     dp[i][j - 1], // j增加，指针移动，i不动
     *     dp[i - 1][j - 1] // i修改，两个指针同时移动
     *     ) + 1 // 操作数+1
     *
     */
    private static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j],
                            dp[i][j - 1],
                            dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    private static int minDistance2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] memo = new int[len1][len2];
        return recursion(word1, word2, len1 - 1, len2 - 1, memo);
    }

    private static int recursion(String word1, String word2, int i, int j, int[][] memo) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = recursion(word1, word2, i - 1, j - 1, memo);
        } else {
            int a = recursion(word1, word2, i - 1, j - 1, memo);
            int b = recursion(word1, word2, i, j - 1, memo);
            int c = recursion(word1, word2, i - 1, j, memo);
            memo[i][j] = min(a, b, c) + 1;
        }
        return memo[i][j];
    }

    private static int min(int v1, int v2, int v3) {
        return Math.min(v1, Math.min(v2, v3));
    }
}
