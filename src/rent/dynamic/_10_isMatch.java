package rent.dynamic;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class _10_isMatch {
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }

    private static int[][] memo;

    public static boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] ps = p.toCharArray();
        memo = new int[ss.length + 1][ps.length + 1];
        return isMatch(0, 0, ss, ps);
    }

    private static boolean isMatch(int i, int j, char[] ss, char[] ps) {
        if (j >= ps.length) {
            return i >= ss.length;
        }
        if (memo[i][j] != 0) {
            return memo[i][j] > 0;
        }
        boolean match = i < ss.length && (ps[j] == ss[i] || ps[j] == '.');
        boolean res;
        if (j + 1 < ps.length && ps[j + 1] == '*') {
            res = isMatch(i, j + 2, ss, ps) // '*'匹配0个
                    || (match && isMatch(i + 1, j, ss, ps)); // '*'匹配多个，i向后移动
        } else {
            res = match && isMatch(i + 1, j + 1, ss, ps);
        }
        memo[i][j] = res ? 1 : -1;
        return res;
    }
}
