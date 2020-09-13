package tree;

import org.junit.Test;
import rent.utils.ArrayUtils;

import java.util.Arrays;

public class Sort {
    @Test
    public void mergeSortTest() {
        int[] array = new int[] { 3, 4, 1, 6, 7, 8, 2 };
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int[] result = new int[array.length];
        divide(array, 0, array.length - 1, result);
    }

    private void divide(int[] array, int start, int end, int[] result) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        divide(array, start, mid, result);
        divide(array, mid + 1, end, result);
        merge(array, start, mid, end, result);
    }

    private void merge(int[] array, int start, int mid, int end, int[] result) {
        int left = start;
        int right = mid + 1;
        int i = left;
        while (left <= mid && right <= end) {
            if (array[left] < array[right]) {
                result[i] = array[left];
                left++;
            } else {
                result[i] = array[right];
                right++;
            }
            i++;
        }
        while (left <= mid) {
            result[i] = array[left];
            left++;
            i++;
        }
        while (right <= end) {
            result[i] = array[right];
            right++;
            i++;
        }
        System.arraycopy(result, start, array, start, end - start + 1);
    }

    @Test
    public void quickSortTest() {
        int[] array = new int[] { 3, 4, 1, 6, 7, 8, 2 };
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        // 对数组进行分区
        int mid = partition(array, start, end);
        // 对前面和后面的部分分别进行分区
        quickSort(array, start, mid);
        quickSort(array, mid + 1, end);
    }

    /**
     * 找到基准点进行分区，分区后，基准点位置的值比前面的都大，比后面的都小
     */
    private int partition(int[] array, int start, int end) {
        int target = array[start];
        int left = start;
        int right = end;
        while (left < right) {
            // 如果后面的值比target大，则right--
            while (left < right && array[right] >= target) {
                right--;
            }
            // 找到比target小的那个值，并赋值给array[left]
            array[left] = array[right];
            // 如果前面的值比target小，则left--
            while (left < right && array[left] <= target) {
                left++;
            }
            // 找到比target大的那个值，并赋值给array[right]
            array[right] = array[left];
        }
        // 当left==right时，该位置即为基准点，赋值为target
        array[left] = target;
        return left;
    }

    @Test
    public void heapSortTest() {
        int[] array = new int[] { 3, 4, 1, 6 };
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        // 构建大顶堆
        buildMaxHeap(array);
        for (int i = array.length - 1; i >= 1; i--) {
            // 将第0个元素交换到到i
            ArrayUtils.swap(array, 0, i);
            // 0发生了变化，重新调整大顶堆
            heapify(array, 0, i);
        }
    }

    private void buildMaxHeap(int[] array) {
        int last = array.length - 1;
        int start = (last - 1) / 2;
        for (int i = start; i >= 0; i--) {
            heapify(array, i, array.length);
        }
    }

    private void heapify(int[] array, int i, int length) {
        int left = 2 * i + 1;
        int right = left + 1;
        int largest = i;
        if (left < length && array[left] > array[i]) {
            largest = left;
        }
        if (right < length && array[right] > array[i]) {
            largest = right;
        }
        if (largest != i) {
            ArrayUtils.swap(array, largest, i);
            // largest发生了变化，重新调整大顶堆
            heapify(array, largest, length);
        }
    }

}
