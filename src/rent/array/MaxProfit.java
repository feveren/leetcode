package rent.array;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit {
    public static void main(String[] args) {
        better(new int[] {7,1,5,3,6,4});
        better(new int[] {1,2,3,4,5});
        better(new int[] {7,6,4,3,1});
    }

    private static void calc(int[] arr) {
        int high = -1;
        int low = Integer.MAX_VALUE;
        int total = 0;
        boolean asc = false;
        for (int i = 0; i < arr.length; i++) {
            if (asc) {
                if (high < arr[i]) {
                    high = arr[i];
                } else {
                    total += high - low;
                    low = arr[i];
                    asc = false;
                }
            } else {
                if (low > arr[i]) {
                    low = arr[i];
                } else {
                    high = arr[i];
                    asc = true;
                }
            }
        }
        if (asc) {
            total += high - low;
        }
        System.out.println(Arrays.toString(arr) + "; total=" + total);
    }

    private static int better(int[] arr) {
        int total = 0;
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i] - arr[i - 1];
            if (value > 0) {
                total += value;
            }
        }
        System.out.println(Arrays.toString(arr) + "; total=" + total);
        return total;
    }
}
