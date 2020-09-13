package rent.dynamic;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 */
public class _435_eraseOverlapIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {
//                {1, 2}, {2, 3}, {3, 4}, {1, 3}
//                {1, 2}, {1, 2}, {1, 2}
//                {1, 2}, {2, 3}
                {1, 2}, {2, 3}, {3, 4}, {-100, -2}, {5, 7}
        };
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);

        int end = Integer.MIN_VALUE;
        int count = 0;
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= end) {
                count++;
                end = interval[1];
            }
        }
        return intervals.length - count;
    }
}
