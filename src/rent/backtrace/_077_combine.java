package rent.backtrace;

import rent.utils.ListUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * https://leetcode-cn.com/problems/combinations/
 */
public class _077_combine {
    public static void main(String[] args) {
        _077_combine helper = new _077_combine();
        List<List<Integer>> result = helper.combine(4, 2);
        System.out.println(ListUtils.toString(result));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>(k);
        backtrack(n, 0, k, path, result);
        return result;
    }

    private void backtrack(int n, int start, int k, Deque<Integer> path, List<List<Integer>> result) {
        if (k == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < n; i++) {
            path.add(i + 1);
            backtrack(n, i + 1, k, path, result);
            path.removeLast();
        }
    }
}
