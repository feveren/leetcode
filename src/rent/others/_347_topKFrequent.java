package rent.others;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class _347_topKFrequent {
    public static void main(String[] args) {
        _347_topKFrequent helper = new _347_topKFrequent();
        int[] array = {1, 1, 4, 3, 3, 1, 3, 4, 4, 5, 2};
        int[] result = helper.topKFrequent(array, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            List<Integer> bucket = buckets[entry.getValue()];
            if (bucket == null) {
                bucket = new ArrayList<>();
                buckets[entry.getValue()] = bucket;
            }
            bucket.add(entry.getKey());
        }
        int[] result = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] == null) {
                continue;
            }
            for (Integer value : buckets[i]) {
                if (index >= k) {
                    break;
                }
                result[index] = value;
                index++;
            }
        }
        return result;
    }
}
