package rent.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
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
        List<List<Integer>> list = combine(4, 3);
        for (List<Integer> l : list) {
            for (Integer i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        backtrack(res, trace, n, k, 1);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> trace, int n, int k, int start) {
        if (trace.size() == k) {
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = start; i <= n; i++) {
            System.out.println("add " + i);
            trace.add(i);
            backtrack(res, trace, n, k, i + 1);
            trace.remove(trace.size() - 1);
        }
    }
}
