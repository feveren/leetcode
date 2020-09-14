package rent.tree;

import rent.utils.ArrayUtils;

import java.util.Arrays;

public class TopK {
    public static void main(String[] args) {
        TopK helper = new TopK();
        int[] array = {3, 4, 1, 5, 7, 2, 0};
        helper.topK(array, 4);
        System.out.println(Arrays.toString(array));

        array = new int[] {3, 4, 1, 5, 7, 2, 0, 4};
        helper.topKQuickSort(array, 3);
        System.out.println(Arrays.toString(array));
    }

    public void topK(int[] array, int k) {
        // 将前k个元素构建成小顶堆
        buildMinHeap(array, k);
        for (int i = k; i < array.length; i++) {
            if (array[i] <= array[0]) {
                continue;
            }
            // 如果元素大于小顶堆最顶上的元素，则交换
            ArrayUtils.swap(array, i, 0);
            // 由于最顶上的元素发生了变化，重新调整小顶堆
            heapify(array, k, i);
        }
    }

    private void buildMinHeap(int[] array, int length) {
        int lastIndex = length - 1;
        int start = (lastIndex - 1) >> 1;
        for (int i = start; i >= 0; i--) {
            heapify(array, length, i);
        }
    }

    private void heapify(int[] array, int length, int i) {
        if (i >= length) {
            return;
        }
        int left = (i << 1) + 1;
        int right = left + 1;
        int small = i;
        if (left < length && array[small] >= array[left]) {
            small = left;
        }
        if (right < length && array[small] >= array[right]) {
            small = right;
        }
        if (small != i) {
            ArrayUtils.swap(array, small, i);
            heapify(array, length, small);
        }
    }

    public void topKQuickSort(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        while (true) {
            int partition = partition(array, start, end);
            if (partition > k) {
                end = partition - 1;
            } else if (partition < k) {
                start = partition + 1;
            } else {
                break;
            }
        }
    }

    private int partition(int[] array, int start, int end) {
        int target = array[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && array[right] <= target) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] >= target) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = target;
        return left;
    }
}
