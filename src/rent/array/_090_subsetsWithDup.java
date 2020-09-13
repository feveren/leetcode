package rent.array;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
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
        int[] nums = new int[] {1, 2, 2};
        List<List<Integer>> res = subsetsWithDup(nums);
        System.out.println(ListUtils.toString(res));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(res, trace, nums, 0);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, LinkedList<Integer> trace, int[] nums, int start) {
        res.add(new ArrayList<>(trace));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            trace.addLast(nums[i]);
            backtrack(res, trace, nums, i + 1);
            trace.removeLast();
        }
    }
}
