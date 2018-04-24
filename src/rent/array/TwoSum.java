package rent.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 *
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {
    public static void main(String[] args) {
        ordered(18, new int[] { 2, 7, 11, 15 });
        System.out.println(Arrays.toString(unordered(new int[] { 7, 15, 2, 11 }, 17)));
    }

    private static void ordered(int sum, int[] array) {
        int start = 0, end = array.length - 1;
        while (start < end) {
            if (array[start] + array[end] > sum) {
                end--;
            }
            else if (array[start] + array[end] < sum) {
                start++;
            }
            else {
                System.out.println(start + ", " + end);
                return;
            }
        }
        System.out.println("not find");
    }

    private static int[] unordered(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>((int) (nums.length * 1.75));
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { map.get(target - nums[i]), i };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
