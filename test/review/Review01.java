package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.model.TreeNode;
import rent.utils.ArrayUtils;
import rent.utils.ListUtils;
import rent.utils.NodeUtils;
import rent.utils.TreeUtils;

import java.util.*;

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
        TreeNode root = TreeUtils.buildTree(new Integer[]{3, 1, 5, null, null, 2, 6});

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
        TreeNode root = TreeUtils.buildTree(new Integer[]{3, 1, 5, null, null, 4, 6});
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
        TreeNode root = TreeUtils.buildTree(new Integer[]{3, 1, 5, null, null, 2, 6});
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
        TreeNode root = TreeUtils.buildTree(new Integer[]{3, 1, 5, null, null, 4, 6});
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
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
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
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
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
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
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
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
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
        int[] array = new int[]{2, 9, 3, 4, 1, 7, 5};
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
        int[] array = new int[]{1, 2, 3, 3, 3, 4, 5, 5, 5, 6, 7, 7};
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

    @Test
    public void deleteDuplicates() {
        ListNode head = NodeUtils.buildStack(new int[]{1, 2, 2, 3, 3, 3, 4});
        NodeUtils.println(deleteDuplicates(head));
        head = NodeUtils.buildStack(new int[]{1, 2, 2, 3, 3, 3, 4});
        NodeUtils.println(deleteDuplicates2(head));
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
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
        System.out.println(hasCycle(node));
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    @Test
    public void detectCycle() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5, 6});
        ListNode n = NodeUtils.findNode(node, 3);
        ListNode last = NodeUtils.findNode(node, 6);
        last.next = n;
        System.out.println(detectCycle(node));
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
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
        slow = slow.next;
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Test
    public void isPalindrome() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 3, 1});
        System.out.println(isPalindrome(node));
    }

    public boolean isPalindrome(ListNode head) {
        ListNode middle = findMiddle(head);
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;
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

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
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
    public void reverseK() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(NodeUtils.toString(reverseK(node, 1)));
    }

    public ListNode reverseK(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        int count = 0;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null && count < k) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        head.next = curr;
        return prev;
    }

    @Test
    public void reverseBetween() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(NodeUtils.toString(reverseBetween(node, 4)));
    }

    public ListNode reverseBetween(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode guard = dummy;
        ListNode pointer = head;
        while (pointer != null) {
            ListNode node = pointer;
            for (int i = 0; i < k - 1 && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }
            for (int i = 0; i < k - 1; i++) {
                ListNode next = pointer.next;
                pointer.next = next.next;
                next.next = guard.next;
                guard.next = next;
            }
            guard = pointer;
            pointer = pointer.next;
        }
        return dummy.next;
    }

    @Test
    public void mergeTwoLists() {
        ListNode node1 = NodeUtils.buildStack(new int[]{1, 3, 4, 6});
        ListNode node2 = NodeUtils.buildStack(new int[]{2, 5, 7});
        System.out.println(NodeUtils.toString(mergeTwoLists(node1, node2)));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                node = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return dummy.next;
    }

    @Test
    public void partition() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 4, 3, 2, 5, 2});
        System.out.println(NodeUtils.toString(partition(node, 3)));
    }

    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(0);
        ListNode lessHead = less;
        ListNode greater = new ListNode(0);
        ListNode greaterHead = greater;
        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                less.next = node;
                less = less.next;
            } else {
                greater.next = node;
                greater = greater.next;
            }
            node = node.next;
        }
        greater.next = null;
        less.next = greaterHead.next;
        return lessHead.next;
    }

    @Test
    public void sortList() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 4, 3, 2, 5, 6});
        System.out.println(NodeUtils.toString(sortList(node)));
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findNodeMiddle(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        left = sortList(left);
        right = sortList(right);
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

    private ListNode findNodeMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void reorderList() {
        ListNode node = NodeUtils.buildStack(new int[]{1, 2, 3, 4, 5});
        System.out.println(NodeUtils.toString(reorderList(node)));
    }

    public ListNode reorderList(ListNode head) {
        ListNode mid = findNodeMiddle(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        right = reverse(right);
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (left != null && right != null) {
            node.next = left;
            left = left.next;
            node = node.next;

            node.next = right;
            right = right.next;
            node = node.next;
        }
        if (left != null) {
            node.next = left;
        }
        return dummy.next;
    }

    @Test
    public void copyRandomList() {
        Node node = buildStack(new int[]{1, 2, 3, 4, 5});
        buildRandom(node);
        println(node);
        System.out.println("start copyRandomList");
        node = copyRandomList(node);
        println(node);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "Node@" + hashCode() + "{" +
                    "val=" + val +
                    ", next=" + (next != null ? next.val : "") +
                    ", random=" + (random != null ? random.val : "") +
                    '}';
        }
    }

    private static Node buildStack(int[] array) {
        Node head = null;
        Node node = null;
        for (int i : array) {
            if (head == null) {
                head = new Node(i);
                node = head;
            } else {
                node.next = new Node(i);
                node = node.next;
            }
        }
        return head;
    }

    private static void buildRandom(Node node) {
        List<Node> list = new ArrayList<>();
        Node n = node;
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

    private static void println(Node node) {
        Node n = node;
        while (n != null) {
            System.out.println(n);
            n = n.next;
        }
    }

    public Node copyRandomList(Node head) {
        Node node = head;
        Map<Node, Node> map = new HashMap<>();
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            Node copy = map.get(node);
            if (node.next != null) {
                copy.next = map.get(node.next);
            }
            if (node.random != null) {
                copy.random = map.get(node.random);
            }
            node = node.next;
        }
        return map.get(head);
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA != null ? nodeA.next : headB;
            nodeB = nodeB != null ? nodeB.next : headA;
        }
        return nodeA;
    }

    @Test
    public void calculate() {
        mIndex = 0;
        System.out.println(calculate("11 + 20 / (2 - 4) + 5"));
    }

    private int mIndex;

    public int calculate(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        char sign = '+';
        int num = 0;
        int length = chars.length;
        while (mIndex < length) {
            char c = chars[mIndex];
            mIndex++;
            boolean isDigit = isDigit(c);
            if (isDigit) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = calculate(s);
            }
            if ((c != ' ' && !isDigit) || mIndex == length) {
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
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    @Test
    public void minWindow() {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = need.getOrDefault(c, 0);
            need.put(c, count + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int start = -1;
        int length = Integer.MAX_VALUE;
        int valid = 0;
        while (right < s.length()) {
            char add = s.charAt(right);
            if (need.containsKey(add)) {
                int count = window.getOrDefault(add, 0) + 1;
                window.put(add, count);
                if (count == need.get(add)) {
                    valid++;
                }
            }
            right++;
            while (valid == need.size()) {
                if (right - left < length) {
                    start = left;
                    length = right - left;
                }
                char remove = s.charAt(left);
                if (need.containsKey(remove)) {
                    int count = window.get(remove) - 1;
                    window.put(remove, count);
                    if (count < need.get(remove)) {
                        valid--;
                    }
                }
                left++;
            }
        }
        return start >= 0 ? s.substring(start, start + length) : null;
    }

    @Test
    public void checkInclusion() {
        System.out.println(checkInclusion2("ab", "eidbaooo"));
        System.out.println(checkInclusion2("ab", "eidboaoo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int count = need.getOrDefault(c, 0) + 1;
            need.put(c, count);
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char add = s2.charAt(right);
            if (need.containsKey(add)) {
                int count = window.getOrDefault(add, 0) + 1;
                window.put(add, count);
                if (count == need.get(add)) {
                    valid++;
                }
            } else {
                window.clear();
                valid = 0;
                left++;
            }
            right++;
            if (valid == need.size()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int count = need.getOrDefault(c, 0) + 1;
            need.put(c, count);
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char add = s2.charAt(right);
            if (need.containsKey(add)) {
                int count = window.getOrDefault(add, 0) + 1;
                window.put(add, count);
                if (count == need.get(add)) {
                    valid++;
                }
            }
            right++;
            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char remove = s2.charAt(left);
                if (need.containsKey(remove)) {
                    int count = window.get(remove) - 1;
                    window.put(remove, count);
                    if (need.containsKey(remove) && count < need.get(remove)) {
                        valid--;
                    }
                }
                left++;
            }
        }
        return false;
    }

    @Test
    public void lengthOfLongestSubstring() {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < s.length()) {
            char add = s.charAt(right);
            int count = window.getOrDefault(add, 0);
            window.put(add, count + 1);
            right++;
            while (window.get(add) > 1) {
                char remove = s.charAt(left);
                window.put(remove, window.get(remove) - 1);
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    @Test
    public void findRepeatNumber() {
        System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int num = nums[i];
            if (num != i) {
                if (nums[num] == num) {
                    return nums[i];
                }
                nums[i] = nums[num];
                nums[num] = num;
            } else {
                i++;
            }
        }
        return -1;
    }

    @Test
    public void merge() {
        int[] array1 = {1, 2, 3, 0, 0, 0};
        int[] array2 = {2, 5, 6};
        merge(array1, 3, array2, 3);
        System.out.println(Arrays.toString(array1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[index] = nums1[i];
                i--;
            } else {
                nums1[index] = nums2[j];
                j--;
            }
            index--;
        }
        if (j >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }
    }

    @Test
    public void longestConsecutive() {
//        System.out.println(longestConsecutive(new int[] {100,4,200,1,3,2}));
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 4, 5, 8, 2, 6, 0, 1}));
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int count = left + right + 1;
            max = Math.max(max, count);
            map.put(num, count);
            if (left > 0) {
                map.put(num - left, count);
            }
            if (right > 0) {
                map.put(num + right, count);
            }
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, 1);
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = 1;
            while (map.containsKey(num - 1)) {
                count++;
                num--;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    @Test
    public void rotate() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(array, 3);
        System.out.println(Arrays.toString(array));

        array = new int[]{-1, -100, 3, 99};
        rotate(array, 2);
        System.out.println(Arrays.toString(array));
    }

    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (k == 0) {
            return;
        }
        int count = 0;
        for (int i = 0; count < length; i++) {
            int prev = nums[i];
            int ptr = i;
            do {
                int next = (ptr + k) % length;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                ptr = next;
                count++;
            } while (ptr != i);
        }
    }

    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    @Test
    public void permute() {
        List<List<Integer>> res = permute(new int[]{1, 2, 3});
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteDfs(nums, res, path, visited);
        return res;
    }

    private void permuteDfs(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
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
            permuteDfs(nums, res, path, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    @Test
    public void permute2() {
        List<List<Integer>> res = permute2(new int[]{1, 1, 2});
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        permuteDfs2(nums, res, path, visited);
        return res;
    }

    private void permuteDfs2(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            permuteDfs2(nums, res, path, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    @Test
    public void combinationSum() {
//        List<List<Integer>> res = combinationSum(new int[]{2,3,6,7}, 7);
//        System.out.println(ListUtils.toString(res));
//        System.out.println();
//        res = combinationSum(new int[]{2,3,5}, 8);
//        System.out.println(ListUtils.toString(res));

        List<List<Integer>> res = combinationSum(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(ListUtils.toString(res));
        System.out.println();
        res = combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumDfs(candidates, target, res, path, 0);
        return res;
    }

    private void combinationSumDfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            combinationSumDfs(candidates, target - candidates[i], res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void combine() {
        List<List<Integer>> res = combine(4, 2);
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combineDfs(n, k, res, path, 1);
        return res;
    }

    private void combineDfs(int n, int k, List<List<Integer>> res, List<Integer> path, int start) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            combineDfs(n, k, res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void subsets() {
        List<List<Integer>> res = subsets(new int[]{1, 2, 3});
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subsetsDfs(nums, res, path, 0);
        return res;
    }

    private void subsetsDfs(int[] nums, List<List<Integer>> res, List<Integer> path, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsDfs(nums, res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void subsets2() {
        List<List<Integer>> res = subsets2(new int[]{1, 2, 1});
        System.out.println(ListUtils.toString(res));
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        subsets2Dfs(nums, res, path, 0);
        return res;
    }

    private void subsets2Dfs(int[] nums, List<List<Integer>> res, List<Integer> path, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            subsets2Dfs(nums, res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void getPermutation() {
        System.out.println(getPermutation(4, 20));
    }

    public String getPermutation(int n, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int[] factor = new int[n];
        factor[0] = 1;
        for (int i = 1; i < n; i++) {
            factor[i] = factor[i - 1] * i;
        }
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        k--;
        StringBuilder builder = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factor[i];
            int num = list.remove(index);
            builder.append(num);
            k -= index * factor[i];
        }
        return builder.toString();
    }

    @Test
    public void floodFill() {
        int[][] image = new int[][] {{1,1,1},{1,1,0},{1,0,1}};
        int[][] res = floodFill(image, 1, 1, 2);
        ArrayUtils.print(res);
    }

    private int[][] directs = new int[][] {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (!inArea(image, sr, sc)) {
            return image;
        }
        if (image[sr][sc] != 1) {
            return image;
        }
        image[sr][sc] = newColor;
        for (int[] d : directs) {
            floodFill(image, sr + d[0], sc + d[1], newColor);
        }
        return image;
    }

    private boolean inArea(int[][] image, int sr, int sc) {
        return sr >= 0 && sr < image.length && sc >= 0 && sc < image[0].length;
    }

    @Test
    public void numIslands() {
        System.out.println(numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        }));
        System.out.println(numIslands(new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        }));
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numIslandsDfs(i, j, grid, visited, directs);
                    count++;
                }
            }
        }
        return count;
    }

    private void numIslandsDfs(int i, int j, char[][] grid, boolean[][] visited, int[][] directs) {
        if (!inArea(i, j, grid)) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int[] d : directs) {
            numIslandsDfs(i + d[0], j + d[1], grid, visited, directs);
        }
    }

    private boolean inArea(int i, int j, char[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    @Test
    public void islandPerimeter() {
        System.out.println(islandPerimeter(new int[][] {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        }));
    }

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return islandPerimeterDfs(i, j, grid, visited);
                }
            }
        }
        return 0;
    }

    private int islandPerimeterDfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (!inArea(grid, i, j)) {
            return 1;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 1;
        }
        visited[i][j] = true;
        int res = 0;
        for (int[] d : directs) {
            res += islandPerimeterDfs(i + d[0], j + d[1], grid, visited);
        }
        return res;
    }

    @Test
    public void maxAreaOfIsland() {
        System.out.println(maxAreaOfIsland(new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        }));
        System.out.println(maxAreaOfIsland(new int[][] {
                {0,0,0,0,0,0,0,0,0}
        }));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = maxAreaOfIslandDfs(i, j, grid, visited);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private int maxAreaOfIslandDfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (!inArea(grid, i, j)) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        visited[i][j] = true;
        int res = 1;
        for (int[] d : directs) {
            res += maxAreaOfIslandDfs(i + d[0], j + d[1], grid, visited);
        }
        return res;
    }

    @Test
    public void openLock() {
        System.out.println(openLock(new String[] {"0201","0101","0102","1212","2002"}, "0202"));
    }

    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<String>(Arrays.asList(deadends));
        if (visited.contains(target)) {
            return -1;
        }
        int step = 0;
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String up = rollUp(curr, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = rollDown(curr, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String rollUp(String str, int i) {
        char[] chars = str.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return new String(chars);
    }

    private String rollDown(String str, int i) {
        char[] chars = str.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return new String(chars);
    }

    @Test
    public void unionFindTest() {
        UnionFind uf = new UnionFind(5);
        uf.union(1, 2);
        uf.print();
        uf.union(3, 4);
        uf.print();
        uf.union(1, 3);
        uf.print();
        System.out.println(uf.connected(1, 3));
    }

    private static class UnionFind {
        int[] parent;
        int[] sizes;
        int count;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.sizes = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
            count = n;
        }

        public void union(int i, int j) {
            i = findRoot(i);
            j = findRoot(j);
            if (sizes[i] > sizes[j]) {
                parent[j] = i;
                sizes[i] += sizes[j];
            } else {
                parent[i] = j;
                sizes[j] += sizes[i];
            }
            count--;
        }

        public boolean connected(int i, int j) {
            return findRoot(i) == findRoot(j);
        }

        private int findRoot(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public int count() {
            return count;
        }

        public void print() {
            System.out.println("count=" + count);
            for (int i = 0; i < parent.length; i++) {
                int x = i;
                System.out.print(i);
                while (x != parent[x]) {
                    x = parent[x];
                    System.out.print(" -> " + x);
                }
                System.out.println(", size=" + sizes[x]);
            }
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(equations.length * 2);
        for (String equation : equations) {
            if (equation.charAt(1) != '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                uf.union(a - 'a', b - 'b');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                if (!uf.connected(a - 'a', b - 'b')) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class MyQueue {
        LinkedList<Integer> stack1 = new LinkedList<Integer>();
        LinkedList<Integer> stack2 = new LinkedList<Integer>();

        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()) {
                transform();
            }
            return stack2.pop();
        }

        private void transform() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        /** Get the front element. */
        public int peek() {
            if (stack2.isEmpty()) {
                transform();
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    class MyStack {
        Queue<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<Integer>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.offer(x);
            int size = queue.size();
            for (int i = 0; i < size - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    @Test
    public void lengthOfLIS() {
        System.out.println(lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void maxSubArray() {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void longestPalindrome() {
        System.out.println(longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int length = -1;
        for (int i = 0; i < chars.length - 1; i++) {
            int[] res1 = getLongest(i, i, chars);
            int[] res2 = getLongest(i, i + 1, chars);
            int[] res = res1[1] > res2[1] ? res1 : res2;
            if (res[1] > length) {
                start = res[0];
                length = res[1];
            }
        }
        return s.substring(start, length);
    }

    private int[] getLongest(int left, int right, char[] chars) {
        while (left >= 0 && right < chars.length&& chars[left] == chars[right]) {
            left--;
            right++;
        }
        return new int[] {left + 1, right - left - 1};
    }

    @Test
    public void longestPalindromeSubseq() {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }

    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

    @Test
    public void minDistance() {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void wordBreak() {
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i] = dp[j] && dict.contains(s.substring(j, i));
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[n];
    }

    @Test
    public void minCut() {
        System.out.println(minCut("aab"));
        System.out.println(minCut("leet"));
    }

    public int minCut(String s) {
        int n = s.length();
        boolean[][] memo = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            memo[i][i] = true;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2) {
                        memo[i][j] = true;
                    } else {
                        memo[i][j] = memo[i + 1][j - 1];
                    }
                }
            }
        }
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (memo[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = i;
            for (int j = 1; j <= i; j++) {
                if (memo[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    @Test
    public void backbag() {
        System.out.println(backbag(10, new int[] {2, 3, 5, 7}, new int[] {1, 5, 2, 4}));
        System.out.println(backbag(10, new int[] {2, 3, 8}, new int[] {2, 5, 8}));
    }

    public int backbag(int w, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][w];
    }

    @Test
    public void canPartition() {
        System.out.println(canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(canPartition(new int[] {1, 2, 3, 5}));
    }

    public boolean canPartition(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        if (count % 2 != 0) {
            return false;
        }
        int n = nums.length;
        count /= 2;
        boolean[][] dp = new boolean[n + 1][count + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= count; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][count];
    }

    @Test
    public void change() {
        System.out.println(change(5, new int[] {1, 2, 5}));
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }

    @Test
    public void minPathSum() {
        System.out.println(minPathSum(new int[][] {{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(minPathSum(new int[][] {{1,2,3},{4,5,6}}));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else {
                    int top = i > 0 ? dp[i - 1][j] : Integer.MAX_VALUE;
                    int left = j > 0 ? dp[i][j - 1] : Integer.MAX_VALUE;
                    dp[i][j] = Math.min(top, left) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void uniquePaths() {
        System.out.println(uniquePaths(3, 2)); // 3
        System.out.println(uniquePaths(7, 3)); // 28
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void uniquePathsWithObstacles() {
        System.out.println(uniquePathsWithObstacles(new int[][] {{0,0,0},{0,1,0},{0,0,0}}));
        System.out.println(uniquePathsWithObstacles(new int[][] {{0,1},{0,0}}));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void canJump() {
        System.out.println(canJump(new int[] {2,3,1,1,4}));
        System.out.println(canJump(new int[] {3,2,1,0,4}));
    }

    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i] + i, max);
            if (max == i) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void jump() {
        System.out.println(jump(new int[] {2,3,1,1,4}));
    }

    public int jump(int[] nums) {
        int max = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(nums[i] + i, max);
            if (i == end) {
                end = max;
                step++;
            }
        }
        return step;
    }

    @Test
    public void threeSum() {
        System.out.println(Arrays.toString(threeSum(new int[] {1, 3, 5, 7, 4, 6}, 17)));
    }

    public int[] threeSum(int[] nums, int sum) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int target = sum - nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int total = nums[left] + nums[right];
                if (total > target) {
                    right--;
                } else if (total < target) {
                    left++;
                } else {
                    return new int[] {nums[i], nums[left], nums[right]};
                }
            }
        }
        return null;
    }

    @Test
    public void findMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums1, nums2);
        }
        int total = m + (n - m + 1) / 2;
        int left = 0;
        int right = m;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = total - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        int i = left;
        int j = total - left;
        int leftMax1 = i > 0 ? nums1[i - 1] : Integer.MIN_VALUE;
        int leftMax2 = j > 0 ? nums2[j - 1] : Integer.MIN_VALUE;
        int rightMin1 = i < m ? nums1[i] : Integer.MAX_VALUE;
        int rightMin2 = j < n ? nums2[j] : Integer.MAX_VALUE;
        if ((m + n) % 2 == 0) {
            return (double) (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2;
        }
        return Math.max(leftMax1, leftMax2);
    }
}