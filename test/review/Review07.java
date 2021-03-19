package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.ArrayUtils;
import rent.utils.ListUtils;
import rent.utils.NodeUtils;
import rent.utils.TreeUtils;

import java.util.*;

public class Review07 {
    @Test
    public void deleteDuplicates() {
        ListNode head = NodeUtils.buildStack(new int[]{1, 2, 2, 3, 3, 3, 4});
        NodeUtils.println(deleteDuplicates(head));
        head = NodeUtils.buildStack(new int[]{1, 2, 2, 3, 3, 3, 4});
        NodeUtils.println(deleteDuplicates2(head));
    }

    private ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null) {
            if (fast.val == slow.val) {
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
            if (slow.next.val == fast.next.val) {
                while (fast.next != null && fast.val == fast.next.val) {
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

    private boolean hasCycle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    private ListNode detectCycle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                hasCycle = true;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        if (!hasCycle) {
            return null;
        }
        slow = slow.next;
        fast = node;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Test // TODO
    public void isPalindrome() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindrome(node));
    }

    private boolean isPalindrome(ListNode head) {
        ListNode left = head;
        ListNode mid = findMiddleNode(head);
        ListNode right = mid.next;
        mid.next = null;
        right = reverseNode(right);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseNode(ListNode head) {
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

    @Test
    public void sortNodeList() {
        ListNode node = NodeUtils.buildStack(new int[]{6, 4, 6, 1, 3, 5});
        node = sortNodeList(node);
        NodeUtils.println(node);
    }

    private ListNode sortNodeList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode mid = findMiddleNode(head);
        ListNode right = mid.next;
        mid.next = null;
        left = sortNodeList(left);
        right = sortNodeList(right);
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
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
        else if (right != null) {
            node.next = right;
        }
        return dummy.next;
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
    public void copyRandomList() {
        Review01.Node node = buildStack(new int[]{1, 2, 3, 4, 5});
        buildRandom(node);
        println(node);
        System.out.println("start copyRandomList");
        node = copyRandomList(node);
        println(node);
    }

    private Review01.Node copyRandomList(Review01.Node head) {
        Map<Review01.Node, Review01.Node> map = new HashMap<>();
        Review01.Node node = head;
        while (node != null) {
            map.put(node, new Review01.Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            Review01.Node n = map.get(node);
            if (node.random != null) {
                n.random = map.get(node.random);
            }
            if (node.next != null) {
                n.next = map.get(node.next);
            }
            node = node.next;
        }
        return map.get(head);
    }

    private static Review01.Node buildStack(int[] array) {
        Review01.Node head = null;
        Review01.Node node = null;
        for (int i : array) {
            if (head == null) {
                head = new Review01.Node(i);
                node = head;
            } else {
                node.next = new Review01.Node(i);
                node = node.next;
            }
        }
        return head;
    }

    private static void println(Review01.Node node) {
        Review01.Node n = node;
        while (n != null) {
            System.out.println(n);
            n = n.next;
        }
    }

    private static void buildRandom(Review01.Node node) {
        List<Review01.Node> list = new ArrayList<>();
        Review01.Node n = node;
        while (n != null) {
            list.add(n);
            n = n.next;
        }
        Collections.reverse(list);

        n = node;
        int index = 0;
        while (n != null) {
            n.random = list.get(index++);
            n = n.next;
        }
    }

    @Test
    public void reverseN() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = reverseN(node, 3);
        NodeUtils.println(listNode);
    }

    private ListNode reverseN(ListNode head, int n) {
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < n && curr != null; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head.next = curr;
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
            ListNode node = ptr;
            for (int i = 0; i < k - 1 && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }
            for (int i = 0; i < k - 1; i++) {
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
    public void calculate() {
        System.out.println(calculate("1 + 2 * 4 + 4 / 2"));
        System.out.println(calculate("(1 + 2) * (4 + 4) / 2"));
    }

    private int mIndex;

    private int calculate(String s) {
        mIndex = 0;
        return calculateInternal(s);
    }

    private int calculateInternal(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        char sign = '+';
        int num = 0;
        int length = s.length();
        while (mIndex < length) {
            char c = s.charAt(mIndex++);
            boolean isNum = c >= '0' && c <= '9';
            if (c == '(') {
                num = calculateInternal(s);
            }
            else if (isNum) {
                num += num * 10 + (c - '0');
            }
            if (mIndex == length || (c != ' ' && !isNum)) {
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

                    default:
                        break;
                }
                sign = c;
                num = 0;
                if (c == ')') {
                    break;
                }
            }
        }
        int size = stack.size();
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += stack.pop();
        }
        return total;
    }

    @Test
    public void levelOrder() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        List<List<Integer>> list = levelOrder(node);
        System.out.println(ListUtils.toString(list));
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (reverse) {
                    list.addFirst(node.val);
                } else {
                    list.addLast(node.val);
                }
            }
            reverse = !reverse;
            result.add(list);
        }
        return result;
    }

    @Test
    public void isValidBST() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3,1,4,null,null,null,5});
        System.out.println(isValidBST(node, null, null));
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
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
    public void quickSort() {
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(array, left, right);
        quickSort(array, left, mid - 1);
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
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    private void heapSort(int[] array) {
        int length = array.length;
        for (int i = length / 2; i >= 0; i--) {
            heapify(array, i, length);
        }
        for (int i = length - 1; i >= 0; i--) {
            ArrayUtils.swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

    public void heapify(int[] array, int parent, int length) {
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
    public void topK() {
        int[] array = {3, 4, 1, 5, 7, 2, 0};
        topK(array, 4);
        System.out.println(Arrays.toString(array));

        array = new int[]{3, 4, 1, 5, 7, 2, 0, 4};
        topK(array, 3);
        System.out.println(Arrays.toString(array));
    }

    private void topK(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = partitionTopK(array, left, right);
            if (mid < k) {
                left = mid + 1;
            } else if (mid > k) {
                right = mid - 1;
            } else {
                return;
            }
        }
    }

    private int partitionTopK(int[] array, int left, int right) {
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

    @Test
    public void reverse() {
        System.out.println(reverse(13));
    }

    public int reverse(int num) {
        System.out.println(Integer.toString(num, 2) + " " + Integer.bitCount(num));
        int result = 0;
        int count = 0;
        while (num > 0) {
            result <<= 1;
            result = (num & 1) | result;
            num >>= 1;
            count++;
        }
        result <<= 31 - count;
        System.out.println(Integer.toString(result, 2));
        return result;
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0) {
            return right + 1;
        }
        if (right == 0) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    @Test // TODO
    public void isBalanced() {
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(isBalance(node));

        node = TreeUtils.buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        System.out.println(isBalance(node));
    }

    public int isBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = isBalance(root.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalance(root.right);
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

    private int mMax;

    public int maxPathSum(TreeNode root) {
        mMax = 0;
        maxPathSumInternal(root);
        return mMax;
    }

    public int maxPathSumInternal(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxPathSumInternal(root.left));
        int right = Math.max(0, maxPathSumInternal(root.right));
        mMax = Math.max(mMax, left + right + root.val);
        return Math.max(left, right) + root.val;
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

    @Test
    public void testCodec() {
        TreeSerialize codec = new TreeSerialize();
        TreeNode treeNode = TreeUtils.buildTree(new Integer[]{1, 2, 3, null, null, 4, 5, null, null, null, null, 6, 7});
        System.out.println(codec.serialize(treeNode));
        System.out.println(TreeUtils.toString(codec.deserialize("1,2,null,null,3,4,6,null,null,7,null,null,5,null,null")));
    }

    public class TreeSerialize {
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            serialize(root, builder);
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }

        private void serialize(TreeNode root, StringBuilder builder) {
            if (root == null) {
                builder.append("null").append(",");
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
            if (index[0] >= array.length) {
                return null;
            }
            String s = array[index[0]];
            index[0]++;
            if ("null".equals(s)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(s));
            root.left = deserialize(array, index);
            root.right = deserialize(array, index);
            return root;
        }
    }

    @Test // TODO
    public void findMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
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
}
