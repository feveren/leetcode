package rent.dynamic;

import java.util.Arrays;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 *
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 *
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *
 * 提示:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 *
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class _1143_longestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
        System.out.println(longestCommonSubsequence("abc", "abc"));
        System.out.println(longestCommonSubsequence("abc", "def"));
    }

    /**
     * dp表
     * dp[i][j]：text1取前i位，text2取前j位的公共子序列长度
     * base case: dp[0][j] = 0  dp[i][0] = 0
     * 选择：字符是否在公共子序列中
     * if text1[i - 1] == text2[j - 1]：一定在公共子序列中，dp[i - 1][j - 1] + 1
     * else：至少有一个不在子序列中，Math.max(dp[i][j - 1], dp[i - 1][j])
     */
    private static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 备忘录 + 递归
     */
    private static int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] memo = new int[len1][len2];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return recursion(text1, text2, len1 - 1, len2 - 1, memo);
    }

    private static int recursion(String text1, String text2, int i, int j, int[][] memo) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (memo[i][j] >= 0) {
            return memo[i][j];
        }
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = recursion(text1, text2, i - 1, j - 1, memo) + 1;
        } else {
            memo[i][j] = Math.max(recursion(text1, text2, i, j - 1, memo),
                    recursion(text1, text2, i - 1, j, memo));
        }
        return memo[i][j];
    }
}
