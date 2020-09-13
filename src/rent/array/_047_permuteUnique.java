package rent.array;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class _047_permuteUnique {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 1};
        List<List<Integer>> res = permuteUnique(nums);
        System.out.println(ListUtils.toString(res));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrace(res, trace, used, nums);
        return res;
    }

    public static void backtrace(List<List<Integer>> res, LinkedList<Integer> trace, boolean[] used, int[] nums) {
        if (trace.size() == nums.length) {
            res.add(new ArrayList<>(trace));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            trace.addLast(nums[i]);
            used[i] = true;
            backtrace(res, trace, used, nums);
            trace.removeLast();
            used[i] = false;
        }
    }
}
