package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.ArrayUtils;
import rent.utils.ListUtils;
import rent.utils.NodeUtils;
import rent.utils.TreeUtils;

import java.util.*;

public class Review04 {

    @Test // TODO
    public void heapSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void heapSort(int[] nums) {
        int length = nums.length;
        int start = length / 2;
        for (int i = start; i >= 0; i--) {
            heapify(nums, i, length);
        }
        for (int i = length - 1; i >= 0; i--) {
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
        if (left < length && nums[largest] < nums[left]) {
            largest = left;
        }
        if (right < length && nums[largest] < nums[right]) {
            largest = right;
        }
        if (largest != parent) {
            ArrayUtils.swap(nums, largest, parent);
            heapify(nums, largest, length);
        }
    }

    @Test // TODO
    public void bubbleSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void bubbleSort(int[] nums) {
        int length = nums.length;
        boolean swaped = false;
        int swapTimes = length - 1;
        for (int i = 0; i < length; i++) {
            int lastSwapIndex = 0;
            for (int j = 0; j < swapTimes; j++) {
                if (nums[j] > nums[j + 1]) {
                    ArrayUtils.swap(nums, j, j + 1);
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

    @Test // TODO
    public void insertionSort() {
        int[] array = {1, 3, 4, 2, 5, 6, 2};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void insertionSort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int curr = nums[i];
            int j;
            for (j = i; j > 0 && curr < nums[j - 1]; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = curr;
        }
    }

    @Test
    public void binarySearch() {
        int[] array = {1, 2, 2, 2, 3, 4};
        System.out.println(binarySearch(array, 2));
        System.out.println(binarySearchLeft(array, 2));
        System.out.println(binarySearchRight(array, 2));
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
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
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
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
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
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
    public void treeSerialize() {
        TreeNode treeNode = TreeUtils.buildTree(new Integer[]{1, 2, 3, null, null, 4, 5, null, null, null, null, 6, 7});
        System.out.println(serialize(treeNode));
        System.out.println(TreeUtils.toString(deserialize("1,2,_,_,3,4,6,_,_,7,_,_,5,_,_")));
    }

    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    private void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("_").append(",");
            return;
        }
        builder.append(root.val).append(",");
        serialize(root.left, builder);
        serialize(root.right, builder);
    }

    public TreeNode deserialize(String text) {
        String[] array = text.split(",");
        return deserialize(array, new int[]{0});
    }

    private TreeNode deserialize(String[] array, int[] index) {
        int i = index[0];
        if (i >= array.length) {
            return null;
        }
        if ("_".equals(array[i])) {
            index[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(array[i]));
        index[0]++;
        node.left = deserialize(array, index);
        node.right = deserialize(array, index);
        return node;
    }

    @Test
    public void levelOrder() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        List<List<Integer>> list = levelOrder(node);
        System.out.println(ListUtils.toString(list));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            res.add(list);
        }
        return res;
    }

    @Test
    public void isValidBST() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{2, 1, 4, null, null, 3, 6});
        System.out.println(isValidBST(node));
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, TreeNode min, TreeNode max) {
        if (node == null) {
            return true;
        }
        if (min != null && min.val >= node.val) {
            return false;
        }
        if (max != null && max.val <= node.val) {
            return false;
        }
        return isValidBST(node.left, min, node) && isValidBST(node.right, node, max);
    }

    @Test
    public void insertIntoBST() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{2, 1, 4, null, null, 3, 6});
        System.out.println(TreeUtils.toString(insertIntoBST(node, 5)));
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
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
    public void maxDepth() {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        TreeNode left = head.left;
        left.left = new TreeNode(15);

        System.out.println(maxDepth(head));
        System.out.println(minDepth(head));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    // TODO
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left != 0 && right != 0) {
            return Math.min(left, right) + 1;
        }
        if (left == 0) {
            return right + 1;
        }
        if (right == 0) {
            return left + 1;
        }
        return 1;
    }

    @Test // TODO
    public void isBalanced() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(isBalanced(node));

        node = TreeUtils.buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        System.out.println(isBalanced(node));
    }

    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = depth(root.right);
        if (right == -1) {
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

    public int maxPathSum(TreeNode node) {
        mMaxPathSum = Integer.MIN_VALUE;
        maxPathSumInternal(node);
        return mMaxPathSum;
    }

    private int maxPathSumInternal(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, maxPathSumInternal(node.left));
        int right = Math.max(0, maxPathSumInternal(node.right));
        mMaxPathSum = Math.max(mMaxPathSum, left + right + node.val);
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

    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

    @Test // TODO
    public void topK() {
        int[] array = {3, 4, 1, 5, 7, 2, 0};
        topK(array, 4);
        System.out.println(Arrays.toString(array));

        array = new int[]{3, 4, 1, 5, 7, 2, 0, 4};
        topK(array, 3);
        System.out.println(Arrays.toString(array));
    }

    public void topK(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = topKPartition(array, left, right);
            if (mid > k) {
                right = mid - 1;
            } else if (mid < k) {
                left = mid + 1;
            } else {
                break;
            }
        }
    }

    private int topKPartition(int[] array, int left, int right) {
        int target = array[left];
        while (left < right) {
            while (left < right && target >= array[right]) {
                right--;
            }
            array[left] = array[right];
            while (left < right && target <= array[left]) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = target;
        return left;
    }

    @Test
    public void topKFreq() {
        int[] array = {1, 1, 4, 3, 3, 1, 3, 4, 4, 5, 2};
        int[] result = topKFreq(array, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] topKFreq(int[] array, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            int count = map.getOrDefault(i, 0);
            map.put(i, count + 1);
        }
        List<Integer>[] buckets = new List[array.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (buckets[entry.getValue()] == null) {
                buckets[entry.getValue()] = new ArrayList<>();
            }
            buckets[entry.getValue()].add(entry.getKey());
        }
        int[] res = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            List<Integer> bucket = buckets[i];
            if (bucket == null) {
                continue;
            }
            for (int j = 0; j < bucket.size() && index < k; j++) {
                res[index] = bucket.get(j);
                index++;
            }
        }
        return res;
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
            if (slow.next.val != fast.next.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                while (fast.next != null && fast.next.val == slow.next.val) {
                    fast = fast.next;
                }
                fast = fast.next;
                slow.next = fast;
            }
        }
        return dummy.next;
    }

    @Test
    public void hasCycle() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5, 6});
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

    @Test // TODO
    public void isPalindromeListNode() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindromeListNode(node));
    }

    public boolean isPalindromeListNode(ListNode node) {
        ListNode middle = findMiddle(node);
        ListNode left = node;
        ListNode right = middle.next;
        middle.next = null;
        right = reverse(right);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode findMiddle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    @Test
    public void reverseN() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = reverseN(node, 3);
        NodeUtils.println(listNode);
    }

    public ListNode reverseN(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        if (head != null) {
            head.next = curr;
        }
        return prev;
    }

    @Test // TODO
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
        while (ptr != null && ptr.next != null) {
            int i;
            ListNode node = ptr;
            for (i = 1; i < k && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }
            for (i = 1; i < k; i++) {
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
            node1 = node1 != null ? node1.next : head2;
            node2 = node2 != null ? node2.next : head1;
        }
        return node1;
    }

    @Test // TODO
    public void sortList() {
        ListNode node = NodeUtils.buildStack(new int[]{6, 4, 6, 1, 3, 5});
        node = sortList(node);
        NodeUtils.println(node);
    }

    private ListNode sortList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode mid = sortListFindMiddle(node);
        ListNode left = node;
        ListNode right = mid.next;
        mid.next = null;
        left = sortList(left);
        right = sortList(right);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        if (left != null) {
            curr.next = left;
        }
        if (right != null) {
            curr.next = right;
        }
        return dummy.next;
    }

    private ListNode sortListFindMiddle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test // TODO
    public void calculate() {
        System.out.println(calculate("(1 + 2) * (4 + 4) / 2"));
    }

    private int mCalcIndex = 0;

    public int calculate(String str) {
        char[] chars = str.toCharArray();
        LinkedList<Integer> stack = new LinkedList<>();
        char sign = '+';
        int num = 0;
        while (mCalcIndex < chars.length) {
            char c = chars[mCalcIndex++];
            boolean isDigit = c >= '0' && c <= '9';
            if (isDigit) {
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                num = calculate(str);
            }
            if ((!isDigit && c != ' ') || mCalcIndex == chars.length) {
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
            }
            if (c == ')') {
                break;
            }
        }
        int size = stack.size();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += stack.pop();
        }
        return sum;
    }

    @Test
    public void rotate() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(rotate(arr, 3)));

        arr = new int[]{-1, -100, 3, 99};
        System.out.println(Arrays.toString(rotate(arr, 2)));
    }

    public int[] rotate(int[] nums, int step) {
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, step - 1);
        reverseArray(nums, step, nums.length - 1);
        return nums;
    }

    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            ArrayUtils.swap(nums, start, end);
            start++;
            end--;
        }
    }

    @Test
    public void findRepeatNumber() {
        System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            ArrayUtils.swap(nums, nums[i], i);
        }
        return -1;
    }

    @Test // TODO
    public void longestConsecutive() {
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            max = Math.max(left + right + 1, max);
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

    @Test // TODO
    public void findMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
            if (nums1[i - 1] < nums2[j]) {
                left = i;
            } else {
                right = i - 1;
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

    @Test // TODO
    public void medianFinder() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    private static class MedianFinder {
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> right = new PriorityQueue<>();

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
            int leftSize = left.size();
            int rightSize = right.size();
            if (leftSize > rightSize) {
                return left.peek();
            } else if (leftSize < rightSize) {
                return right.peek();
            }
            if (leftSize == 0 && rightSize == 0) {
                return 0;
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
        int[] res = new int[nums.length - k + 1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!queue.isEmpty() && nums[queue.peek()] < num) {
                queue.removeLast();
            }
            queue.addLast(i);
            if (i < k - 1) {
                continue;
            }
            res[j] = nums[queue.peekFirst()];
            j++;
            if (queue.peekFirst() == i - k + 1) {
                queue.removeFirst();
            }
        }
        return res;
    }

    @Test
    public void minWindow() {
        String src = "ADOBECODEBANC";
        String target = "ABC";
//        String src = "a";
//        String target = "aa";
        System.out.println(minWindow(src, target));
    }

    private String minWindow(String src, String target) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            int count = need.getOrDefault(c, 0);
            need.put(c, count + 1);
        }
        int length = src.length();
        int left = 0, right = 0;
        int valid = 0;
        int start = -1, minLength = Integer.MAX_VALUE;
        while (right < length) {
            char add = src.charAt(right);
            if (need.containsKey(add)) {
                int total = window.getOrDefault(add, 0) + 1;
                window.put(add, total);
                if (total == need.get(add)) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (right - left < minLength) {
                    start = left;
                    minLength = right - left;
                }
                char remove = src.charAt(left);
                if (need.containsKey(remove)) {
                    int curr = window.get(remove);
                    window.put(remove, curr - 1);
                    if (curr == need.get(remove)) {
                        valid--;
                    }
                }
                left++;
            }
            right++;
        }
        if (start >= 0 && minLength > 0) {
            return src.substring(start, start + minLength + 1);
        }
        return null;
    }

    @Test
    public void permute() {
        boolean[] visited = new boolean[3];
        permute(new int[]{1, 2, 3}, visited);
        System.out.println(ListUtils.toString(res));

        System.out.println(permuteUnique(new int[]{1, 2, 2}));
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public void permute(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            permute(nums, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        res.clear();
        path.clear();
        boolean[] visited = new boolean[3];
        permuteUnique(nums, visited);
        return res;
    }

    public void permuteUnique(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            permuteUnique(nums, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    @Test
    public void combinationSum() {
        List<List<Integer>> result = combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(ListUtils.toString(result));

        result = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(ListUtils.toString(result));

        result = combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(ListUtils.toString(result));
    }

    // TODO
    private List<List<Integer>> combinationSum(int[] nums, int sum) {
        res.clear();
        path.clear();
        combinationSum(nums, sum, 0, 0);
        return res;
    }

    private void combinationSum(int[] nums, int sum, int start, int total) {
        if (total == sum) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (total + nums[i] > sum) {
                continue;
            }
            total += nums[i];
            path.add(nums[i]);
            combinationSum(nums, sum, i, total);
            total -= nums[i];
            path.remove(path.size() - 1);
        }
    }

    // TODO
    private List<List<Integer>> combinationSum2(int[] nums, int sum) {
        Arrays.sort(nums);
        res.clear();
        path.clear();
        combinationSum2(nums, sum, 0, 0);
        return res;
    }

    private void combinationSum2(int[] nums, int sum, int start, int total) {
        if (sum == total) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (total + nums[i] > sum) {
                continue;
            }
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            total += nums[i];
            path.add(nums[i]);
            combinationSum2(nums, sum, i + 1, total);
            total -= nums[i];
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void subsets() {
        List<List<Integer>> result = subsetsWithDup(new int[] {1, 2, 2});
        System.out.println(ListUtils.toString(result));
    }

    private List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        res.clear();
        path.clear();
        subsetsWithDup(nums, 0);
        return res;
    }

    private void subsetsWithDup(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDup(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void equationsPossible() {
        System.out.println(equationsPossible(new String[]{"a==b","b!=a"}));
        System.out.println(equationsPossible(new String[]{"a==b","b==c","a==c"}));
    }

    private boolean equationsPossible(String[] array) {
        UnionFind uf = new UnionFind(26);
        for (String s : array) {
            if (s.charAt(1) == '=') {
                int a = s.charAt(0) - 'a';
                int b = s.charAt(3) - 'a';
                uf.connect(a, b);
            }
        }
        for (String s : array) {
            if (s.charAt(1) == '!') {
                int a = s.charAt(0) - 'a';
                int b = s.charAt(3) - 'a';
                if (uf.isConnected(a, b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class UnionFind {
        int[] parent;
        int[] sizes;

        public UnionFind(int size) {
            parent = new int[size];
            sizes = new int[size];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
        }

        public void connect(int p, int q) {
            int rootP = findParent(p);
            int rootQ = findParent(q);
            if (sizes[rootP] > sizes[rootQ]) {
                parent[rootQ] = rootP;
                sizes[rootP] += sizes[rootQ];
            } else {
                parent[rootP] = rootQ;
                sizes[rootQ] += sizes[rootP];
            }
        }

        private int findParent(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            return findParent(p) == findParent(q);
        }
    }

    @Test
    public void lengthOfLIS() {
        int[] array = new int[] {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(array));
    }

    private int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    @Test
    public void maxSubArray() {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    private int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    @Test
    public void longestPalindrome() {
        System.out.println(longestPalindrome("abbac"));
        System.out.println(longestPalindrome("abcac"));
        System.out.println(longestPalindrome("abzac"));
    }

    private String longestPalindrome(String str) {
        int n = str.length();
        int start = 0, maxLength = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] count1 = max(str, i, i);
            int[] count2 = max(str, i, i + 1);
            int[] max = count1[1] > count2[1] ? count1 : count2;
            if (max[1] > maxLength) {
                start = max[0];
                maxLength = max[1];
            }
        }
        return str.substring(start, start + maxLength);
    }

    private int[] max(String str, int left, int right) {
        while (left >= 0 && right < str.length() &&
                str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return new int[] {left + 1, right - left - 1};
    }

    @Test
    public void longestPalindromeSubseq() {
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));
    }

    private int longestPalindromeSubseq(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    if (j - i <= 2) {
                        dp[i][j] = j - i + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    @Test // TODO
    public void minCut() {
        System.out.println(minCut("aab"));
        System.out.println(minCut("abac"));
        System.out.println(minCut("abdc"));
        System.out.println(minCut("abdd"));
        System.out.println(minCut("abbab"));
    }

    private int minCut(String str) {
        int n = str.length();
        boolean[][] memo = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            memo[i][i] = true;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    if (j - i <= 2) {
                        memo[i][j] = true;
                    } else {
                        memo[i][j] = memo[i + 1][j - 1];
                    }
                } else {
                    memo[i][j] = false;
                }
            }
        }
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (memo[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (memo[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}