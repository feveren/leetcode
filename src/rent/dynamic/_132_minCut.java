package rent.dynamic;

/**
 * 132. 分割回文串 II
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 */
public class _132_minCut {
    public static void main(String[] args) {
        _132_minCut helper = new _132_minCut();
        System.out.println(helper.minCut("aab"));
        System.out.println(helper.minCut("abac"));
        System.out.println(helper.minCut("abdc"));
        System.out.println(helper.minCut("abdd"));
        System.out.println(helper.minCut("abbab"));
    }

    public int minCut(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        boolean[][] memo = new boolean[len][len];
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    memo[i][j] = false;
                } else {
                    if (j - i <= 2) {
                        memo[i][j] = true;
                    } else {
                        memo[i][j] = memo[i + 1][j - 1];
                    }
                }
            }
        }
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (memo[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 1; j--) {
                if (memo[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    public int minCut2(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (isPartition(s, 0, i)) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 1; j--) {
                if (isPartition(s, j, i)) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    private boolean isPartition(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
