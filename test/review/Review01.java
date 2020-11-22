package review;

import org.junit.Test;
import rent.model.TreeNode;
import rent.utils.ArrayUtils;
import rent.utils.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class Review01 {
    @Test
    public void traverse() {
        TreeNode root = new TreeNode(1);
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode lastVisit = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.offer(node);
                node = node.left;
            }
//            if (!stack.isEmpty()) {
//                node = stack.pop();
//                if (node.right == null || node.right == lastVisit) {
//                    lastVisit = node;
//                    node = null;
//                } else {
//                    stack.push(node);
//                    node = node.right;
//                }
//            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    @Test
    public void isValidBST() {
        TreeNode root = TreeUtils.buildTree(new Integer[]{3,1,5,null,null,2,6});

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        int last = Integer.MIN_VALUE;
        boolean res = true;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (last > node.val) {
                    res = false;
                    break;
                }
                last = node.val;
                node = node.right;
            }
        }
        System.out.println(res);
    }

    private int mLastVal;

    @Test
    public void isValidBSTRecursive() {
        TreeNode root = TreeUtils.buildTree(new Integer[]{3,1,5,null,null,4,6});
        mLastVal = Integer.MIN_VALUE;
        System.out.println(isValidBSTRecursive(root));
    }

    private boolean isValidBSTRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBSTRecursive(root.left)) {
            return false;
        }
        if (mLastVal > root.val) {
            return false;
        }
        mLastVal = root.val;
        if (!isValidBSTRecursive(root.right)) {
            return false;
        }
        return true;
    }

    @Test
    public void isValidBST2Test() {
        TreeNode root = TreeUtils.buildTree(new Integer[]{3,1,5,null,null,2,6});
        System.out.println(isValidBST2(root, null, null));
    }

    private boolean isValidBST2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val < min.val) {
            return false;
        }
        if (max != null && root.val > max.val) {
            return false;
        }
        return isValidBST2(root.left, min, root) && isValidBST2(root.right, root, max);
    }

    @Test
    public void insertIntoBST() {
        TreeNode root = TreeUtils.buildTree(new Integer[]{3,1,5,null,null,4,6});
        TreeNode node = insertIntoBST(root, 2);
        System.out.println(TreeUtils.toString(node));
    }

    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    @Test
    public void heapSortTest() {
        int[] array = new int[] {2, 9, 3, 4, 1, 7, 5};
        int length = array.length;
        int start = length / 2 - 1;
        for (int i = start; i >= 0; i--) {
            heapify(array, i, length);
        }
        for (int i = length - 1; i >= 0; i--) {
            ArrayUtils.swap(array, 0, i);
            heapify(array, 0, i);
        }
        System.out.println(Arrays.toString(array));
    }

    private void heapify(int[] array, int i, int length) {
        if (i >= length) {
            return;
        }
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int target = i;
        if (left < length && array[left] > array[target]) {
            target = left;
        }
        if (right < length && array[right] > array[target]) {
            target = right;
        }
        if (target != i) {
            ArrayUtils.swap(array, target, i);
            heapify(array, target, length);
        }
    }

    @Test
    public void topKTest() {
        int[] array = new int[] {2, 9, 3, 4, 1, 7, 5};
        int k = 5;
        for (int i = k / 2 - 1; i >= 0; i--) {
            heapifyMin(array, i, k);
        }
        for (int i = k; i < array.length; i++) {
            if (array[i] > array[0]) {
                ArrayUtils.swap(array, 0, i);
                heapifyMin(array, 0, k);
            }
        }
        System.out.println(Arrays.toString(array));
    }

    private void heapifyMin(int[] array, int i, int length) {
        int left = i * 2 + 1;
        int right = left + 1;
        int smallest = i;
        if (left < length && array[left] < array[smallest]) {
            smallest = left;
        }
        if (right < length && array[right] < array[smallest]) {
            smallest = right;
        }
        if (smallest != i) {
            ArrayUtils.swap(array, smallest, i);
            heapifyMin(array, smallest, length);
        }
    }

    // TODO
    @Test
    public void topKTest2() {
        int[] array = new int[] {2, 9, 3, 4, 1, 7, 5};
        quickSortTopK(array, 4);
        System.out.println(Arrays.toString(array));
    }

    private void quickSortTopK(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (true) {
            int mid = partition2(array, left, right);
            if (mid > k) {
                right = mid - 1;
            } else if (mid < k) {
                left = mid + 1;
            } else {
                break;
            }
        }
    }

    private int partition2(int[] array, int left, int right) {
        int target = array[left];
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

    // TODO
    @Test
    public void quickSortTest() {
        int[] array = new int[] {2, 9, 3, 4, 1, 7, 5};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(array, left, right);
        quickSort(array, left, mid);
        quickSort(array, mid + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int target = array[left];
        while (left < right) {
            while (left < right && array[right] >= target) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] <= target) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = target;
        return left;
    }

    // TODO
    @Test
    public void mergeSort() {
        int[] array = new int[] {2, 9, 3, 4, 1, 7, 5};
        divideAndMerge(array, 0, array.length - 1, new int[array.length]);
        System.out.println(Arrays.toString(array));
    }

    private void divideAndMerge(int[] array, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        divideAndMerge(array, left, mid, temp);
        divideAndMerge(array, mid + 1, right, temp);
        merge(array, left, mid, right, temp);
    }

    private void merge(int[] array, int start, int mid, int end, int[] temp) {
        int left = start;
        int right = mid + 1;
        int i = left;
        while (left <= mid && right <= end) {
            if (array[left] < array[right]) {
                temp[i] = array[left];
                left++;
            } else {
                temp[i] = array[right];
                right++;
            }
            i++;
        }
        while (left <= mid) {
            temp[i] = array[left];
            left++;
            i++;
        }
        while (right <= end) {
            temp[i] = array[right];
            right++;
            i++;
        }
        System.arraycopy(temp, start, array, start, end - start + 1);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxLeft, maxRight) + 1;
    }

    // TODO
    public boolean isBalanced(TreeNode root) {
        return maxDepth2(root) != -1;
    }

    private int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = maxDepth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    @Test
    public void binarySearch() {
        int[] array = new int[] {1, 2, 3, 3, 3, 4, 5, 5, 5, 6, 7, 7};
        int target = 7;
        System.out.println(binarySearch(array, target));
        System.out.println(binarySearchLeft(array, target));
        System.out.println(binarySearchRight(array, target));
    }

    private int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int binarySearchLeft(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left >= array.length || array[left] != target) {
            return -1;
        }
        return left;
    }

    private int binarySearchRight(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        if (right < 0 || array[right] != target) {
            return -1;
        }
        return right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    private int mMaxValue;

    public int maxPathSum(TreeNode root) {
        mMaxValue = Integer.MIN_VALUE;
        maxPathSumDivide(root);
        return mMaxValue;
    }

    public int maxPathSumDivide(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(maxPathSumDivide(root.left), 0);
        int right = Math.max(maxPathSumDivide(root.right), 0);
        mMaxValue = Math.max(mMaxValue, left + root.val + right);
        return Math.max(left, right) + root.val;
    }
}
