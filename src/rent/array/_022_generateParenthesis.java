package rent.array;

import rent.utils.ListUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class _022_generateParenthesis {
    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        System.out.println(ListUtils.toString(list, "\n"));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        char[] trace = new char[n * 2];
        backtrack(res, trace, n, n, 0);
        return res;
    }

    private static void backtrack(List<String> res, char[] trace, int left, int right, int index) {
        if (left == 0 && right == 0) {
            res.add(new String(trace));
            return;
        }
        if (left > 0) {
            trace[index] = '(';
            backtrack(res, trace, left - 1, right, index + 1);
            trace[index] = 0;
        }
        if (left < right && right > 0) {
            trace[index] = ')';
            backtrack(res, trace, left, right - 1, index + 1);
            trace[index] = 0;
        }
    }
}
