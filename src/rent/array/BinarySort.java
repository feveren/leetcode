package rent.array;

public class BinarySort {
    public static void main(String[] args) {
        int[] array = new int[] { 4, 4, 4, 4, 4 };
        int target = 0;
        System.out.println(binary(array, target));
        System.out.println(binaryLeft(array, target));
        System.out.println(binaryRight(array, target));
    }

    private static int binary(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            else if (array[mid] > target) {
                right = mid - 1;
            }
            else if (array[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int binaryLeft(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid - 1;
            }
            else if (array[mid] > target) {
                right = mid - 1;
            }
            else if (array[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= array.length || array[left] != target) {
            return -1;
        }
        return left;
    }

    private static int binaryRight(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                left = mid + 1;
            }
            else if (array[mid] > target) {
                right = mid - 1;
            }
            else if (array[mid] < target) {
                left = mid + 1;
            }
        }
        if (right < 0 || array[right] != target) {
            return -1;
        }
        return right;
    }
}
