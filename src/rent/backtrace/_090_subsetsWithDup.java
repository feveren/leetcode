package rent.backtrace;

import rent.utils.ListUtils;

import java.util.*;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * https://leetcode-cn.com/problems/subsets-ii/
 */
public class _090_subsetsWithDup {
    public static void main(String[] args) {
        _090_subsetsWithDup helper = new _090_subsetsWithDup();
        List<List<Integer>> result = helper.subsetsWithDup(new int[] {1, 2, 2});
        System.out.println(ListUtils.toString(result));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(nums, 0, path, result);
        return result;
    }

    private void backtrack(int[] nums, int start, Deque<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtrack(nums, i + 1, path, result);
            path.removeLast();
        }
    }
}
