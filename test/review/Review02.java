package review;

import org.junit.Test;
import rent.model.ListNode;
import rent.utils.ArrayUtils;
import rent.utils.NodeUtils;

import java.util.Arrays;
import java.util.LinkedList;

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
}