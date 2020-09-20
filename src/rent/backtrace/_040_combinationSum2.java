package rent.backtrace;

import rent.utils.ListUtils;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class _040_combinationSum2 {
    public static void main(String[] args) {
        _040_combinationSum2 helper = new _040_combinationSum2();
        List<List<Integer>> result = helper.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);

        System.out.println(ListUtils.toString(result));
        result = helper.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(ListUtils.toString(result));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int length = candidates == null ? 0 : candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        if (length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>(length);
        backtrack(candidates, 0, target, path, result);
        return result;
    }

    private void backtrack(int[] candidates, int start, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (num > target) {
                continue;
            }
            if (i > start && num == candidates[i - 1]) {
                continue;
            }
            path.add(num);
            backtrack(candidates, i + 1, target - num, path, result);
            path.removeLast();
        }
    }
}
