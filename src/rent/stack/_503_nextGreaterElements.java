package rent.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 *
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 */
public class _503_nextGreaterElements {
    public static void main(String[] args) {
        _503_nextGreaterElements helper = new _503_nextGreaterElements();
        System.out.println(Arrays.toString(helper.nextGreaterElements(new int[] {1,2,1})));
    }

    public int[] nextGreaterElements(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int length = nums.length;
        int[] res = new int[length];
        for (int i = 2 * length - 1; i >= 0; i--) {
            int index = i % length;
            while (!stack.isEmpty() && stack.peek() <= nums[index]) {
                stack.pop();
            }
            res[index] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[index]);
        }
        return res;
    }
}
