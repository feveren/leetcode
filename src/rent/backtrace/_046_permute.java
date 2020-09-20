package rent.backtrace;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * https://leetcode-cn.com/problems/permutations/
 */
public class _046_permute {
    public static void main(String[] args) {
        _046_permute helper = new _046_permute();
        List<List<Integer>> result = helper.permute(new int[]{1, 2, 3});
        System.out.println(ListUtils.toString(result));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, path, result);
        return result;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
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
