package rent.backtrace;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
        _047_permuteUnique helper = new _047_permuteUnique();
        int[] nums = new int[] {1, 2, 1};
        List<List<Integer>> res = helper.permuteUnique(nums);
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, path, result);
        return result;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (nums.length == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, used, path, result);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
