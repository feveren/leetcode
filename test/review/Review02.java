package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.ArrayUtils;
import rent.utils.NodeUtils;
import rent.utils.TreeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Review02 {
    @Test
    public void quickSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public void quickSort(int[] array, int left, int right) {
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

    @Test
    public void heapSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void heapSort(int[] array) {
        int length = array.length;
        int start = length / 2;
        for (int i = start; i >= 0; i--) {
            heapify(array, i, length);
        }
        for (int i = length - 1; i >= 0; i--) {
            ArrayUtils.swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

    private void heapify(int[] array, int parent, int length) {
        if (parent >= length) {
            return;
        }
        int left = 2 * parent + 1;
        int right = left + 1;
        int largest = parent;
        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != parent) {
            ArrayUtils.swap(array, largest, parent);

            heapify(array, largest, length);
        }
    }

    @Test
    public void bubbleSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void bubbleSort(int[] array) {
        int length = array.length;
        int swapTimes = length - 1;
        for (int i = 0; i < length; i++) {
            boolean swaped = false;
            int lastSwapIndex = 0;
            for (int j = 0; j < swapTimes; j++) {
                if (array[j] > array[j + 1]) {
                    ArrayUtils.swap(array, j, j + 1);
                    swaped = true;
                    lastSwapIndex = j;
                }
            }
            swapTimes = lastSwapIndex;
            if (!swaped) {
                break;
            }
        }
    }

    @Test
    public void binarySearch() {
        int[] array = {1, 2, 2, 2, 3, 4, 4, 5, 6};
        System.out.println(binarySearch(array, 2));
        System.out.println(binarySearchLeft(array, 2));
        System.out.println(binarySearchRight(array, 2));
    }

    public int binarySearch(int[] array, int target) {
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

    public int binarySearchLeft(int[] array, int target) {
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

    public int binarySearchRight(int[] array, int target) {
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

    @Test
    public void reverse() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        NodeUtils.println(reverseListNode(node));
        node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        NodeUtils.println(reverseK(node, 3));
        node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        NodeUtils.println(reverseBetween(node, 3));
    }

    public ListNode reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseK(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        int i = 0;
        while (curr != null && i < k) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }
        if (head != null) {
            head.next = curr;
        }
        return prev;
    }

    public ListNode reverseBetween(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode ptr = head;
        while (ptr != null && ptr.next != null) {
            ListNode node = ptr;
            int count = 0;
            while (node != null && count <= k) {
                node = node.next;
                count++;
            }
            if (count < k) {
                break;
            }
            for (int i = 1; i < k && ptr.next != null; i++) {
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

    @Test
    public void hasCycle() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(hasCycle(node));
        System.out.println(detectCycle(node));
        ListNode n = NodeUtils.findNode(node, 3);
        ListNode last = NodeUtils.findNode(node, 6);
        last.next = n;
        System.out.println(hasCycle(node));
        System.out.println(detectCycle(node));
    }

    public boolean hasCycle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        fast = node;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Test
    public void findNodeK() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        System.out.println(findNodeK(node, 3));
        System.out.println(findMidNode(node));
    }

    public ListNode findNodeK(ListNode head, int k) {
        ListNode fast = head;
        int i = 0;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (fast == null) {
            return null;
        }
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void sortListNode() {
        ListNode node = NodeUtils.buildStack(new int[]{6, 3, 2, 5, 4});
        NodeUtils.println(sortListNode(node));
    }

    public ListNode sortListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode mid = findMidNode(head);
        ListNode right = mid.next;
        mid.next = null;

        left = sortListNode(left);
        right = sortListNode(right);

        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (left != null && right != null) {
            if (left.val > right.val) {
                node.next = right;
                right = right.next;
            } else {
                node.next = left;
                left = left.next;
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
    public void deleteDuplicates() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 3, 7, 4, 4, 5});
        node = deleteDuplicates2(node);
        NodeUtils.println(node);

        node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 3, 7, 4, 4, 5});
        node = deleteDuplicates3(node);
        NodeUtils.println(node);
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null) {
            while (node.next != null && node.val == node.next.val) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val == fast.val) {
                fast = fast.next;
                slow.next = fast;
            } else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null && fast.next != null) {
            if (slow.next.val != fast.next.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                while (fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                fast = fast.next;
                slow.next = fast;
            }
        }
        return dummy.next;
    }

    @Test
    public void calculate() {
        System.out.println(calculate("5 + 2 * (3 - 4) / 2 "));
    }

    private int mIndex = 0;

    public int calculate(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int num = 0;
        char sign = '+';
        int length = chars.length;
        for (; mIndex < length; mIndex++) {
            char c = chars[mIndex];
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            if (c == '(') {
                mIndex++;
                num = calculate(s);
            }
            if (c == ' ' && c != length - 1) {
                continue;
            }
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
            sign = c;
            num = 0;

            if (c == ')') {
                mIndex++;
                break;
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            count += stack.pop();
        }
        return count;
    }

    @Test
    public void isValidBST() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3,1,4,null,null,null,5});
        System.out.println(isValidBST(node, null, null));
    }

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }

    @Test
    public void insertIntoBST() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3,1,4,null,null,null,5});
        System.out.println(TreeUtils.toString(insertIntoBST(node, 2)));
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

//    @Test
//    public void maxDepth() {
//        TreeNode head = new TreeNode(3);
//        head.left = new TreeNode(9);
//        head.right = new TreeNode(20);
//        TreeNode right = head.right;
//        right.left = new TreeNode(15);
//        right.right = new TreeNode(7);
//        System.out.println(maxDepth(head));
//    }
//
//    public int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int left = maxDepth(root.left);
//        int right = maxDepth(root.right);
//        return Math.max(left, right) + 1;
//    }

    @Test
    public void isBalanced() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(isBalanced(node));

        node = TreeUtils.buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        System.out.println(isBalanced(node));
    }

    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        if (left < 0) {
            return -1;
        }
        int right = maxDepth(root.right);
        if (right < 0) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    @Test
    public void maxPathSum() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println(maxPathSum(node));

        node = TreeUtils.buildTree(new Integer[]{-10, 2, 3});
        System.out.println(maxPathSum(node));
    }

    private int mMaxPathSum;

    public int maxPathSum(TreeNode root) {
        mMaxPathSum = Integer.MIN_VALUE;
        calcMaxPathSum(root);
        return mMaxPathSum;
    }

    public int calcMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(calcMaxPathSum(root.left), 0);
        int right = Math.max(calcMaxPathSum(root.right), 0);
        mMaxPathSum = Math.max(mMaxPathSum, root.val + left + right);
        return root.val + Math.max(left, right);
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
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
    public void findTopK() {
        int[] array = {3, 4, 1, 5, 7, 2, 0};
        findTopK(array, 2);
        System.out.println(Arrays.toString(array));
    }

    public void findTopK(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = partition(array, left, right);
            if (mid > k) {
                right = mid - 1;
            } else if (mid < k) {
                left = mid + 1;
            } else {
                return;
            }
        }
    }

    @Test
    public void topKFrequent() {
        int[] array = {1, 1, 4, 3, 3, 1, 3, 4, 4, 5, 2};
        int[] result = topKFrequent(array, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        List<Integer>[] freqs = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            List<Integer> freq = freqs[entry.getValue()];
            if (freq == null) {
                freq = new ArrayList<>();
                freqs[entry.getValue()] = freq;
            }
            freq.add(entry.getKey());
        }
        int[] res = new int[k];
        int j = 0;
        for (int i = freqs.length - 1; i >= 0; i--) {
            if (freqs[i] == null) {
                continue;
            }
            for (Integer freq : freqs[i]) {
                res[j] = freq;
                j++;
                if (j == k) {
                    return res;
                }
            }
        }
        return res;
    }

    @Test
    public void maxSlidingWindow() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (i >= k - 1) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
            int remove = i - k;
            if (remove >= 0) {
                if (queue.peekFirst() == remove) {
                    queue.pollFirst();
                }
            }
        }
        return res;
    }

    @Test
    public void dailyTemperatures() {
        // 1, 1, 4, 2, 1, 1, 0, 0
        System.out.println(Arrays.toString(dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73})));
    }

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    @Test
    public void nextGreaterElement() {
        int[] nums1 = new int[] {4,1,2};
        int[] nums2 = new int[] {1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        LinkedList<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[stack.peek()] <= nums2[i]) {
                stack.pop();
            }
            int value = stack.isEmpty() ? -1 : nums2[stack.peek()];
            map.put(nums2[i], value);
            stack.push(i);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    @Test
    public void nextGreaterElement2() {
        System.out.println(Arrays.toString(nextGreaterElement(new int[] {1,2,1})));
    }

    public int[] nextGreaterElement(int[] nums) {
        int n = nums.length * 2;
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[nums.length];
        for (int i = n - 1; i >= 0; i--) {
            int raw = i % nums.length;
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[raw]) {
                stack.pop();
            }
            res[raw] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(raw);
        }
        return res;
    }

    @Test
    public void findMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        System.out.println(findMedianSortedArrays(new int[] {1, 4, 7, 9}, new int[] {2, 5, 6, 8}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (n < m) {
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
        int j = leftCount - i;
        int leftMax1 = i - 1 >= 0 ? nums1[i - 1] : Integer.MIN_VALUE;
        int leftMax2 = j - 1 >= 0 ? nums2[j - 1] : Integer.MIN_VALUE;
        int rightMin1 = i < m ? nums1[i] : Integer.MAX_VALUE;
        int rightMin2 = j < n ? nums2[j] : Integer.MAX_VALUE;
        if ((m + n) % 2 == 0) {
            return (double) (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2;
        }
        return Math.max(leftMax1, leftMax2);
    }

    class MedianFinder {
        private PriorityQueue<Integer> left, right;

        public MedianFinder() {
            left = new PriorityQueue<>((a, b) -> b - a);
            right = new PriorityQueue<>();
        }

        void addNum(int num) {
            if (left.size() > right.size()) {
                left.add(num);
                right.add(left.poll());
            } else {
                right.add(num);
                left.add(right.poll());
            }
        }

        double findMedian() {
            int m = left.size();
            int n = right.size();
            if (m == 0 && n == 0) {
                return 0;
            }
            if (m > n) {
                return left.peek();
            } else if (m < n) {
                return right.peek();
            }
            return (double) (left.peek() + right.peek()) / 2;
        }
    }

    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> mapping;

        public RandomizedSet() {
            list = new ArrayList<>();
            mapping = new HashMap<>();
        }

        void insert(int val) {
            if (mapping.containsKey(val)) {
                return;
            }
            int index = list.size();
            list.add(val);
            mapping.put(val, index);
        }

        void remove(int val) {
            if (!mapping.containsKey(val)) {
                return;
            }
            int remove = mapping.get(val);
            int last = list.size() - 1;
            int lastValue = list.get(last);
            mapping.put(lastValue, remove);
            list.set(remove, lastValue);
            list.remove(last);
        }

        int getRandom() {
            int size = list.size();
            int index = (int) (Math.random() * size);
            return list.get(index);
        }
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
                builder.append("_").append(",");
                return;
            }
            builder.append(node.val).append(",");
            serialize(node.left, builder);
            serialize(node.right, builder);
        }

        public TreeNode deserialize(String str) {
            if (str == null || str.trim().equals("")) {
                return null;
            }
            String[] array = str.split(",");
            return deserialize(array, new int[] {0});
        }

        public TreeNode deserialize(String[] array, int[] index) {
            if (index[0] >= array.length) {
                return null;
            }
            String s = array[index[0]];
            if (s.equals("_")) {
                index[0]++;
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(s));
            index[0]++;
            node.left = deserialize(array, index);
            node.right = deserialize(array, index);
            return node;
        }
    }

    @Test
    public void findRepeatNumber() {
        System.out.println(findRepeatNumber(new int[] {2, 3, 1, 0, 2, 5, 3}));
    }

    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i && nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            while (nums[i] != i) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
            i++;
        }
        return -1;
    }

    @Test
    public void longestConsecutive() {
        System.out.println(longestConsecutive2(new int[] {100,4,200,1,3,2,6}));
        System.out.println(longestConsecutive2(new int[] {0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, 1);
        }
        int max = 0;
        for (int num : nums) {
            if (map.get(num) > 1) {
                continue;
            }
            int left = num - 1;
            int count = 1;
            while (map.containsKey(left)) {
                count += map.get(left);
                if (map.get(left) > 1) {
                    break;
                }
                left--;
            }
            map.put(num, count);
            max = Math.max(max, count);
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int count = left + right + 1;
            if (left > 0) {
                map.put(num - left, count);
            }
            if (right > 0) {
                map.put(num + right, count);
            }
            map.put(num, count);
            max = Math.max(max, count);
        }
        return max;
    }

    @Test
    public void readAndCount() {
        String path = "C:\\Users\\rentao01\\Desktop\\1.txt";
        File file = new File(path);
        FileInputStream inputStream = null;
        try {
            NumberAdder numberAdder = new NumberAdder();
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int num = 0;
            int len;
            boolean positive = true;
            while ((len = inputStream.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    byte b = buffer[i];
                    if (b == '\r' || b == '\n' || b == ' ') {
                        numberAdder.addNum(positive ? num : -num);
                        positive = true;
                        num = 0;
                    } else if (b == '-') {
                        positive = false;
                    } else {
                        num = num * 10 + (b - '0');
                    }
                }
            }
            if (num != 0) {
                numberAdder.addNum(num);
            }
            System.out.println(numberAdder.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class NumberAdder {
        private StringBuilder builder = new StringBuilder();

        public void addNum(int num) {
            int i = 0;
            while (true) {
                if (i >= builder.length()) {
                    builder.append('0');
                }
                num += builder.charAt(i) - '0';
                if (num < 10) {
                    builder.setCharAt(i, (char) (num + '0'));
                    return;
                }
                builder.setCharAt(i, (char) (num % 10 + '0'));
                num /= 10;
                i++;
            }
        }

        public String getTotal() {
            return new StringBuilder(builder).reverse().toString();
        }
    }

    @Test
    public void sub() {
        System.out.println(sub("123", "321"));
        System.out.println(sub("321", "123"));
        System.out.println(sub("321", "9"));
        System.out.println(sub("321", "321"));
        System.out.println(sub("121", "99"));
    }

    public String sub(String a, String b) {
        int compare = compare(a, b);
        if (compare == 0) {
            return "0";
        }
        boolean positive = true;
        if (compare < 0) {
            String tmp = a;
            a = b;
            b = tmp;
            positive = false;
        }
        int len1 = a.length();
        int len2 = b.length();
        StringBuilder res = new StringBuilder(len1);
        int i = len1 - 1, j = len2 - 1;
        int extra = 0;
        while (true) {
            if (i < 0) {
                break;
            }
            int v1 = a.charAt(i) - '0' + extra;
            extra = 0;
            if (j < 0) {
                res.append(v1);
                i--;
                break;
            }
            int v2 = b.charAt(j) - '0';
            if (v1 >= v2) {
                res.append(v1 - v2);
            } else {
                extra = -1;
                res.append(v1 + 10 - v2);
            }
            i--;
            j--;
        }
        while (i >= 0) {
            res.append(a.charAt(i));
            i--;
        }
        if (res.charAt(res.length() - 1) == '0') {
            res.deleteCharAt(res.length() - 1);
        }
        if (!positive) {
            res.append("-");
        }
        res.reverse();
        return res.toString();
    }

    private int compare(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }
        int length = a.length();
        for (int i = 0; i < length; i++) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if (c1 == c2) {
                continue;
            }
            return c1 - c2;
        }
        return 0;
    }
}