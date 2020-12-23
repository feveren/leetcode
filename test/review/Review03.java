package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.NodeUtils;
import rent.utils.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class Review03 {
    @Test
    public void deleteDuplicates() {
        // TODO review again
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 3, 7, 4, 4, 5});
        node = deleteDuplicates1(node);
        NodeUtils.println(node);

        node = NodeUtils.buildStack(new int[]{3, 3, 3, 7, 4, 4, 5});
        node = deleteDuplicates2(node);
        NodeUtils.println(node);
    }

    private ListNode deleteDuplicates1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val == fast.val) {
                fast = fast.next;
                slow.next = fast;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return head;
    }

    private ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null && fast.next != null) {
            if (slow.next.val != fast.next.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                while (fast.next != null && slow.next.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void maxPathSum() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println(maxPathSum(node));

        node = TreeUtils.buildTree(new Integer[]{-10, 2, 3});
        System.out.println(maxPathSum(node));
    }

    private int mMaxPathSum;

    private int maxPathSum(TreeNode node) {
        mMaxPathSum = Integer.MIN_VALUE;
        maxPathSumCalc(node);
        return mMaxPathSum;
    }

    private int maxPathSumCalc(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(maxPathSumCalc(node.left), 0);
        int right = Math.max(maxPathSumCalc(node.right), 0);
        mMaxPathSum = Math.max(mMaxPathSum, node.val + left + right);
        return Math.max(left, right) + node.val;
    }

    @Test
    public void lowestCommonAncestor() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode p = TreeUtils.findNode(node, 5);
        TreeNode q = TreeUtils.findNode(node, 1);
        System.out.println(lowestCommonAncestor(node, p, q));

        node = TreeUtils.buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        p = TreeUtils.findNode(node, 5);
        q = TreeUtils.findNode(node, 4);
        System.out.println(lowestCommonAncestor(node, p, q));
    }

    private TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node == p || node == q) {
            return node;
        }
        TreeNode left = lowestCommonAncestor(node.left, p, q);
        TreeNode right = lowestCommonAncestor(node.right, p, q);
        if (left != null && right != null) {
            return node;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    @Test
    public void dailyTemperatures() {
        // 1, 1, 4, 2, 1, 1, 0, 0
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    private int[] dailyTemperatures(int[] nums) {
        int[] res = new int[nums.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    @Test
    public void findMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1, 4, 7, 9}, new int[]{2, 5, 6, 8}));
    }

    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int leftCount = m + (n - m + 1) / 2;
        int left = 0;
        int right = m;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = leftCount - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        int i = left;
        int j = leftCount - left;
        int leftMax1 = i - 1 >= 0 ? nums1[i - 1] : Integer.MIN_VALUE;
        int leftMax2 = j - 1 >= 0 ? nums2[j - 1] : Integer.MIN_VALUE;
        int rightMin1 = i < m ? nums1[i] : Integer.MAX_VALUE;
        int rightMin2 = j < n ? nums2[j] : Integer.MAX_VALUE;
        if ((m + n) % 2 == 0) {
            return (double) (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2;
        }
        return Math.max(leftMax1, leftMax2);
    }

    @Test
    public void trap() {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    private int trap(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int leftMax = nums[left];
        int rightMax = nums[right];
        int count = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, nums[left]);
            rightMax = Math.max(rightMax, nums[right]);
            if (leftMax < rightMax) {
                count += leftMax - nums[left];
                left++;
            } else {
                count += rightMax - nums[right];
                right--;
            }
        }
        return count;
    }

    @Test
    public void quickSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(nums, left, right);
        quickSort(nums, left, mid);
        quickSort(nums, mid + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int target = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= target) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= target) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = target;
        return left;
    }

    @Test
    public void binarySearch() {
        int[] array = {1, 2, 2, 3, 3, 3, 4};
        System.out.println(binarySearch(array, 3));
        System.out.println(binarySearchLeft(array, 3));
        System.out.println(binarySearchRight(array, 3));
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int binarySearchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        if (left < nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

    public int binarySearchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right >= 0 && nums[right] == target) {
            return right;
        }
        return -1;
    }

    @Test
    public void testCodec() {
        Codec codec = new Codec();
        TreeNode treeNode = TreeUtils.buildTree(new Integer[]{1, 2, 3, null, null, 4, 5, null, null, null, null, 6, 7});
        System.out.println(codec.serialize(treeNode));
        System.out.println(TreeUtils.toString(codec.deserialize("1,2,null,null,3,4,6,null,null,7,null,null,5,null,null")));
    }

    class Codec {
        public String serialize(TreeNode node) {
            StringBuilder builder = new StringBuilder();
            serialize(node, builder);
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }

        private void serialize(TreeNode node, StringBuilder builder) {
            if (node == null) {
                builder.append("null").append(",");
                return;
            }
            builder.append(node.val).append(",");
            serialize(node.left, builder);
            serialize(node.right, builder);
        }

        public TreeNode deserialize(String text) {
            String[] array = text.split(",");
            return deserialize(array, new int[] {0});
        }

        private TreeNode deserialize(String[] array, int[] index) {
            if (index[0] >= array.length) {
                return null;
            }
            String val = array[index[0]];
            if ("null".equals(val)) {
                index[0]++;
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(val));
            index[0]++;
            node.left = deserialize(array, index);
            node.right = deserialize(array, index);
            return node;
        }
    }

    @Test
    public void maxSubArray() {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = nums[i] + dp[i - 1];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    @Test
    public void mul() {
        System.out.println(mul("99", "999"));
    }

    public String mul(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int num1 = a.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int num2 = b.charAt(j) - '0';
                int mul = num1 * num2;
                mul += res[i + j + 1];
                res[i + j + 1] = mul % 10;
                res[i + j] += mul / 10;
            }
        }
        StringBuilder builder = new StringBuilder();
        boolean removeZero = true;
        for (int i = 0; i < res.length; i++) {
            if (removeZero && res[i] == 0) {
                continue;
            }
            removeZero = false;
            builder.append(res[i]);
        }
        return builder.toString();
    }
}
