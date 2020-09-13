package rent.array;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
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
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> res = subsets(nums);
        System.out.println(ListUtils.toString(res));
        System.out.println(res.size());
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(res, trace, nums, 0);
        return res;
    }

    private static void backtrace(List<List<Integer>> res, LinkedList<Integer> trace, int[] nums, int start) {
        res.add(new ArrayList<>(trace));

        for (int i = start; i < nums.length; i++) {
            trace.addLast(nums[i]);
            backtrace(res, trace, nums, i + 1);
            trace.removeLast();
        }
    }
}
