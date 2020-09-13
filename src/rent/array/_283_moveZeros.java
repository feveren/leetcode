package rent.array;

import rent.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes
 */
public class _283_moveZeros {
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 0, 3, 12};
        method4(array);
        System.out.println(Arrays.toString(array));
    }

    private static void method1(int[] array) {
        int length;
        if (array == null || (length = array.length) <= 1) {
            return;
        }
        int slot = -1;
        for (int i = 0; i < length; i++) {
            int value = array[i];
            if (value == 0) {
                if (slot < 0) {
                    slot = i;
                }
            } else if (slot >= 0) {
                ArrayUtils.swap(array, slot, i);
                slot++;
            }
        }
    }

    private static void method2(int[] array) {
        int length;
        if (array == null || (length = array.length) <= 1) {
            return;
        }
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] != 0) {
                array[index] = array[i];
                index++;
            }
        }
        for (int i = index; i < array.length; i++) {
            array[i] = 0;
        }
    }

    private static void method3(int[] array) {
        int length;
        if (array == null || (length = array.length) <= 1) {
            return;
        }
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] != 0) {
                array[index] = array[i];
                array[i] = 0;
                index++;
            }
        }
    }

    private static void method4(int[] array) {
        int length;
        if (array == null || (length = array.length) <= 1) {
            return;
        }
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] != 0) {
                if (i > index) {
                    array[index] = array[i];
                    array[i] = 0;
                }
                index++;
            }
        }
    }
}
