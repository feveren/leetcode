package rent.backtrace;

import rent.utils.ListUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class _039_combinationSum {
    public static void main(String[] args) {
        _039_combinationSum helper = new _039_combinationSum();
        List<List<Integer>> result = helper.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(ListUtils.toString(result));

        result = helper.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(ListUtils.toString(result));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>(candidates.length);
        backtrack(candidates, 0, target, path, result);
        return result;
    }

    private void backtrack(int[] candidates, int start, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], path, result);
            path.removeLast();
        }
    }
}
