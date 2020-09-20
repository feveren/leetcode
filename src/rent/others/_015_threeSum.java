package rent.others;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * https://leetcode-cn.com/problems/3sum/
 */
public class _015_threeSum {
    public static void main(String[] args) {
        _015_threeSum helper = new _015_threeSum();
        List<List<Integer>> lists = helper.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(ListUtils.toString(lists));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            // 数组已经排序，如果nums[k]为最小值，如果大于0，则不可能三数之和等于0
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum > 0) {
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    j--;
                } else if (sum < 0) {
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    i++;
                } else {
                    results.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    j--;
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    i++;
                }
            }
        }
        return results;
    }
}
