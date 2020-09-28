package rent.dynamic;

public class _005_longestPalindrome {
    public static void main(String[] args) {
        _005_longestPalindrome helper = new _005_longestPalindrome();
        System.out.println(helper.longestPalindrome("abbac"));
        System.out.println(helper.longestPalindrome("abcac"));
        System.out.println(helper.longestPalindrome("abzac"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        int start = 0, maxLength = 1;
        for (int i = 0; i < length; i++) {
            int[] ret1 = maxPalindrome(chars, i, i);
            int[] ret2 = maxPalindrome(chars, i, i + 1);
            int[] max = ret1[1] > ret2[1] ? ret1 : ret2;
            if (max[1] > maxLength) {
                start = max[0];
                maxLength = max[1];
            }
        }
        return s.substring(start, start + maxLength);
    }

    private int[] maxPalindrome(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        return new int[] {i + 1, j - i - 1};
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[length][length];
        int start = 0, maxLength = 1;
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    start = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
}
