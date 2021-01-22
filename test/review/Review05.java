package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.ArrayUtils;
import rent.utils.ListUtils;
import rent.utils.NodeUtils;
import rent.utils.TreeUtils;

import java.util.*;

public class Review05 {
    @Test
    public void heapSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void heapSort(int[] nums) {
        int length = nums.length;
        for (int i = length / 2; i >= 0; i--) {
            heapify(nums, i, length);
        }
        for (int i = length - 1; i > 0; i--) {
            ArrayUtils.swap(nums, 0, i);
            heapify(nums, 0, i);
        }
    }

    private void heapify(int[] nums, int parent, int length) {
        if (parent >= length) {
            return;
        }
        int left = 2 * parent + 1;
        int right = left + 1;
        int largest = parent;
        if (left < length && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < length && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != parent) {
            ArrayUtils.swap(nums, largest, parent);
            heapify(nums, largest, length);
        }
    }

    @Test
    public void bubbleSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void bubbleSort(int[] nums) {
        int length = nums.length;
        boolean swaped = false;
        int lastSwapIndex = length - 1;
        for (int i = 0; i < length; i++) {
            int last = lastSwapIndex;
            for (int j = 0; j < lastSwapIndex; j++) {
                if (nums[j] > nums[j + 1]) {
                    ArrayUtils.swap(nums, j, j + 1);
                    swaped = true;
                    last = j;
                }
            }
            if (!swaped) {
                break;
            }
            lastSwapIndex = last;
        }
    }

    @Test // TODO
    public void insertionSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int j;
            for (j = i; j > 0 && curr < nums[j - 1]; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = curr;
        }
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
        quickSort(nums, 0, mid - 1);
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

    @Test // TODO
    public void maxDepth() {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        TreeNode left = head.left;
        left.left = new TreeNode(15);

        System.out.println(maxDepth(head));
        System.out.println(minDepth(head));
    }

    private int maxDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int left = maxDepth(head.left);
        int right = maxDepth(head.right);
        return Math.max(left, right) + 1;
    }

    private int minDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }
        int left = minDepth(head.left);
        int right = minDepth(head.right);
        if (left == 0) {
            return right + 1;
        }
        if (right == 0) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    @Test
    public void isBalanced() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(isBalanced(node));

        node = TreeUtils.buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        System.out.println(isBalanced(node));
    }

    private boolean isBalanced(TreeNode node) {
        return isBalancedTraversal(node) != -1;
    }

    private int isBalancedTraversal(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = isBalancedTraversal(node.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalancedTraversal(node.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    @Test
    public void topK() {
        int[] array = {3, 4, 1, 5, 7, 2, 0};
        topK(array, 4);
        System.out.println(Arrays.toString(array));

        array = new int[]{3, 4, 1, 5, 7, 2, 0, 4};
        topK(array, 3);
        System.out.println(Arrays.toString(array));
    }

    private void topK(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = topKPartition(nums, left, right);
            if (mid > k) {
                right = mid - 1;
            } else if (mid < k) {
                left = mid + 1;
            } else {
                break;
            }
        }
    }

    private int topKPartition(int[] nums, int left, int right) {
        int target = nums[left];
        while (left < right) {
            while (left < right && nums[right] <= target) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] >= target) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = target;
        return left;
    }

    @Test // TODO
    public void deleteDuplicates() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 1, 1, 3});
        NodeUtils.println(deleteDuplicates(node));

        node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 3, 7, 4, 4, 5});
        NodeUtils.println(deleteDuplicates2(node));
    }

    private ListNode deleteDuplicates(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null) {
            if (slow.val == fast.val) {
                fast = fast.next;
                slow.next = fast;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return node;
    }

    private ListNode deleteDuplicates2(ListNode node) {
        ListNode dummy = new ListNode(0);
        dummy.next = node;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null && fast.next != null) {
            if (slow.next.val == fast.next.val) {
                while (fast.next != null && slow.next.val == fast.next.val) {
                    fast = fast.next;
                }
                fast = fast.next;
                slow.next = fast;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void isPalindromeListNode() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 2, 1});
        System.out.println(isPalindromeListNode(node));
    }

    private boolean isPalindromeListNode(ListNode root) {
        ListNode mid = findMiddle(root);
        ListNode left = root;
        ListNode right = mid.next;
        mid.next = null;
        right = reverse(right);
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode findMiddle(ListNode root) {
        ListNode slow = root;
        ListNode fast = root.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode root) {
        ListNode prev = null;
        ListNode curr = root;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    @Test
    public void getIntersectionNode() {
        ListNode same = NodeUtils.buildStack(new int[]{1, 2, 3});
        ListNode node1 = NodeUtils.buildStack(new int[]{7, 4});
        ListNode node2 = NodeUtils.buildStack(new int[]{2, 3, 7});
        node1 = NodeUtils.append(node1, same);
        node2 = NodeUtils.append(node2, same);
        NodeUtils.println(node1);
        NodeUtils.println(node2);
        ListNode node = getIntersectionNode(node1, node2);
        System.out.println(node);
    }

    private ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        ListNode node1 = head1;
        ListNode node2 = head2;
        while (node1 != node2) {
            node1 = node1 == null ? head2 : node1.next;
            node2 = node2 == null ? head1 : node2.next;
        }
        return node1;
    }

    @Test
    public void sortList() {
        ListNode node = NodeUtils.buildStack(new int[]{6, 4, 6, 1, 3, 5});
        node = sortList(node);
        NodeUtils.println(node);
    }

    private ListNode sortList(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        ListNode mid = findMiddle(root);
        ListNode next = mid.next;
        mid.next = null;
        ListNode left = sortList(root);
        ListNode right = sortList(next);
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        if (left != null) {
            node.next = left;
        }
        if (right != null) {
            node.next = right;
        }
        return dummy.next;
    }

    @Test
    public void reverseBetween() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = reverseBetween(node, 3);
        NodeUtils.println(listNode);
    }

    private ListNode reverseBetween(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode ptr = head;
        while (ptr != null) {
            ListNode node = ptr;
            for (int i = 1; i < k && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }
            for (int i = 1; i < k; i++) {
                ListNode next = ptr.next;
                ptr.next = next.next;
                next.next = guard.next;
                guard.next = next;
            }
            guard = ptr;
            ptr = ptr.next;
        }
        return dummy.next;
    }

    @Test // TODO
    public void calculate() {
        System.out.println(calculate("(1 + 2) * (4 + 4) / 2"));
    }

    private int mIndex;

    private int calculate(String s) {
        int length = s.length();
        int num = 0;
        char sign = '+';
        LinkedList<Integer> stack = new LinkedList<>();
        while (mIndex < length) {
            char c = s.charAt(mIndex);
            mIndex++;
            boolean isDigit = c >= '0' && c <= '9';
            if (isDigit) {
                num = num * 10 + (c - '0');
            }
            else if (c == '(') {
                num = calculate(s);
            }
            if ((!isDigit && c != ' ') || mIndex == length) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    @Test
    public void longestConsecutive() {
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    private int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            max = Math.max(max, left + right + 1);
            if (left > 0) {
                map.put(num - left, max);
            }
            if (right > 0) {
                map.put(num + right, max);
            }
            map.put(num, max);
        }
        return max;
    }

    @Test
    public void findMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
    }

    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int leftCount = m + (n - m + 1) / 2;
        int left = 0;
        int right = m - 1;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = leftCount - i;
            if (nums1[i - 1] < nums2[j]) {
                left = i;
            } else {
                right = i - 1;
            }
        }
        int i = left;
        int j = leftCount - left;
        int leftMax1 = i > 0 ? nums1[i - 1] : Integer.MIN_VALUE;
        int leftMax2 = j > 0 ? nums2[j - 1] : Integer.MIN_VALUE;
        int rightMin1 = i < m ? nums1[i] : Integer.MAX_VALUE;
        int rightMin2 = j < n ? nums2[j] : Integer.MAX_VALUE;
        if ((m + n) % 2 == 0) {
            return (double) (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2;
        }
        return Math.max(leftMax1, leftMax2);
    }

    @Test
    public void medianFinder() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    private static class MedianFinder {
        private PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a);
        private PriorityQueue<Integer> right = new PriorityQueue<>();

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
            else if (m < n) {
                return right.peek();
            }
            return (double) (left.peek() + right.peek()) / 2;
        }
    }

    @Test // TODO
    public void maxSlidingWindow() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] {1, -1}, 1)));
    }

    private int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int length = nums.length;
        int[] res = new int[length - k + 1];
        int index = 0;
        for (int i = 0; i < length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
            if (i < k - 1) {
                continue;
            }
            res[index] = nums[queue.peekFirst()];
            index++;
            if (queue.peekFirst() == i - k + 1) {
                queue.removeFirst();
            }
        }
        return res;
    }

    @Test // TODO
    public void combinationSum() {
        List<List<Integer>> result = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(ListUtils.toString(result));

        result = combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(ListUtils.toString(result));
    }

    private List<List<Integer>> combinationSum2(int[] nums, int sum) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        combinationSum2Dfs(nums, sum, 0, res, new ArrayList<>());
        return res;
    }

    private void combinationSum2Dfs(int[] nums, int sum, int start, List<List<Integer>> res, List<Integer> path) {
        if (sum == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (sum < nums[i]) {
                continue;
            }
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            combinationSum2Dfs(nums, sum - nums[i], i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    @Test // TODO
    public void minCut() {
        System.out.println(minCut("aab"));
        System.out.println(minCut("abac"));
        System.out.println(minCut("abdc"));
        System.out.println(minCut("abdd"));
        System.out.println(minCut("abbab"));
    }

    private int minCut(String s) {
        int len = s.length();
        boolean[][] memo = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    memo[i][j] = j - i <= 2 || memo[i + 1][j - 1];
                }
            }
        }
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (memo[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
            for (int j = 1; j < i; j++) {
                if (memo[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    @Test // TODO
    public void wordBreak() {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    private boolean wordBreak(String str, List<String> list) {
        Set<String> dict = new HashSet<>(list);
        int len = str.length();
        boolean[] dp = new boolean[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                String s = str.substring(j, i + 1);
                if (dict.contains(s)) {
                    dp[i] = j == 0 || dp[j - 1];
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[len - 1];
    }

    @Test // TODO
    public void pick() {
        System.out.println(pick(3, 4, new int[] {2, 1, 3}, new int[] {4, 2, 3}));
    }

    private int pick(int maxSize, int maxWeight, int[] weight, int[] values) {
        int[][] dp = new int[maxSize + 1][maxWeight + 1];
        for (int i = 1; i <= maxSize; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[maxSize][maxWeight];
    }

    @Test // TODO
    public void canPartition() {
        System.out.println(canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(canPartition(new int[] {1, 2, 3, 5}));
    }

    private boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        int len = nums.length;
        boolean[][] dp = new boolean[len + 1][sum + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][sum];
    }

    @Test // TODO
    public void coinChange() {
        System.out.println(coinChange(new int[] { 1, 2, 5 }, 11));
        System.out.println(coinChange(new int[] { 2 }, 3));
    }

    private int coinChange(int[] coins, int sum) {
        int len = coins.length;
        int[] dp = new int[sum + 1];
        for (int i = 1; i <= sum; i++) {
            dp[i] = sum + 1;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j >= coins[i - 1]) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[sum] == sum + 1 ? -1 : dp[sum];

//        int len = coins.length;
//        int[][] dp = new int[len + 1][sum + 1];
//        for (int i = 0; i <= len; i++) {
//            for (int j = 1; j <= sum; j++) {
//                dp[i][j] = Integer.MAX_VALUE;
//            }
//        }
//        for (int i = 1; i <= len; i++) {
//            for (int j = 1; j <= sum; j++) {
//                if (coins[i - 1] > j) {
//                    dp[i][j] = dp[i - 1][j];
//                } else {
//                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
//                }
//            }
//        }
//        return dp[len][sum] == Integer.MAX_VALUE ? -1 : dp[len][sum];
    }

    @Test
    public void trap() {
        System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[] {4,2,0,3,2,5}));
    }

    private int trap(int[] array) {
        int len = array.length;
        int left = 0;
        int right = len - 1;
        int leftMax = array[left];
        int rightMax = array[right];
        int sum = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, array[left]);
            rightMax = Math.max(rightMax, array[right]);
            if (leftMax < rightMax) {
                sum += leftMax - array[left];
                left++;
            } else {
                sum += rightMax - array[right];
                right--;
            }
        }
        return sum;
    }

    @Test
    public void mul() {
        System.out.println(mul("123", "321"));
    }

    private String mul(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int a = str1.charAt(i) - '0';
                int b = str2.charAt(j) - '0';
                int mul = a * b + res[i + j + 1];
                res[i + j + 1] = mul % 10;
                res[i + j] += mul / 10;
            }
        }
        StringBuilder builder = new StringBuilder();
        boolean remove = true;
        for (int value : res) {
            if (value == 0 && remove) {
                continue;
            }
            remove = false;
            builder.append(value);
        }
        return builder.toString();
    }
}
