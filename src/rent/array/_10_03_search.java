package rent.array;

/**
 * 面试题 10.03. 搜索旋转数组
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。
 * 若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 *
 * 示例2:
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 *
 * 提示:
 * arr 长度范围在[1, 1000000]之间
 *
 * https://leetcode-cn.com/problems/search-rotate-array-lcci/
 */
public class _10_03_search {
    public static void main(String[] args) {
        _10_03_search helper = new _10_03_search();
        int[] nums = {5,5,5,1,2,3,4,5};
        System.out.println(helper.search(nums, 5));
    }

    public int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (arr[left] == target) {
                return left;
            }
            int mid = left + (right - left) / 2;
            if (arr[left] < arr[mid]) {
                if (arr[left] <= target && target <= arr[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            else if (arr[left] > arr[mid]) {
                if (arr[mid] <= target && target <= arr[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            else {
                left++;
            }
        }
        return -1;
    }
}
