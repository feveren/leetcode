package rent.backtrace;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 60. 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 *
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * https://leetcode-cn.com/problems/permutation-sequence/
 */
public class _060_getPermutation {
    public static void main(String[] args) {
        _060_getPermutation helper = new _060_getPermutation();
        System.out.println(helper.getPermutation(3, 3));
        System.out.println(helper.getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        k--;
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        List<Integer> source = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            source.add(i + 1);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / fact[i];
            builder.append(source.remove(index));
            k -= index * fact[i];
        }
        return builder.toString();
    }

    public String getPermutation2(int n, int k) {
        Deque<String> result = new ArrayDeque<>();
        StringBuilder path = new StringBuilder(n);
        boolean[] used = new boolean[n];
        backtrack(n, k, used, path, result);
        return result.getLast();
    }

    private boolean backtrack(int n, int k, boolean[] used, StringBuilder path, Deque<String> result) {
        if (path.length() == n) {
            result.add(path.toString());
            return result.size() == k;
        }
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.append(i + 1);
            flag = backtrack(n, k, used, path, result);
            if (flag) {
                break;
            }
            used[i] = false;
            path.deleteCharAt(path.length() - 1);
        }
        return flag;
    }
}
