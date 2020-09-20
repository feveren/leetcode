package rent.backtrace;

import rent.utils.ListUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * https://leetcode-cn.com/problems/subsets/
 */
public class _078_subsets {
    public static void main(String[] args) {
        _078_subsets helper = new _078_subsets();
        List<List<Integer>> result = helper.subsets(new int[] {1, 2, 3});
        System.out.println(ListUtils.toString(result));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(nums, 0, path, result);
        return result;
    }

    private void backtrack(int[] nums, int start, Deque<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path, result);
            path.removeLast();
        }
    }
}
