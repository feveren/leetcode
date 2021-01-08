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
        return deserialize(array, new int[] {0});
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
        TreeNode node = TreeUtils.buildTree(new Integer[]{2,1,4,null,null,3,6});
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
        TreeNode node = TreeUtils.buildTree(new Integer[]{2,1,4,null,null,3,6});
        System.out.println(TreeUtils.toString(insertIntoBST(node, 5)));
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        else if (root.val < val) {
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

        array = new int[] {3, 4, 1, 5, 7, 2, 0, 4};
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

    @Test
    public void reverseBetween() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = reverseBetween(node, 2);
        NodeUtils.println(listNode);
    }

    private ListNode reverseBetween(ListNode head, int k) {

        return null;
    }
}
