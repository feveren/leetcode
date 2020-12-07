package rent.array;

import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 */
public class _295_MedianFinder {
    public static void main(String[] args) {
        _295_MedianFinder.MedianFinder medianFinder = new _295_MedianFinder.MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    static class MedianFinder {
        PriorityQueue<Integer> left, right;

        /** initialize your data structure here. */
        public MedianFinder() {
            left = new PriorityQueue<>((a, b) -> b - a);
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (left.size() > right.size()) {
                left.offer(num);
                right.offer(left.poll());
            } else {
                right.offer(num);
                left.offer(right.poll());
            }
        }

        public double findMedian() {
            int m = left.size();
            int n = right.size();
            if (m == 0 && n == 0) {
                return 0;
            }
            if (m > n) {
                return left.peek();
            }
            if (m < n) {
                return right.peek();
            }
            return (double) (left.peek() + right.peek()) / 2;
        }
    }
}
