package rent.array;

import rent.model.Node;

public class StackCircle {
    public static void main(String[] args) {
        Node<String> stack1 = new Node<>("1");
        Node<String> stack2 = new Node<>("2");
        Node<String> stack3 = new Node<>("3");
        Node<String> stack4 = new Node<>("4");
        Node<String> stack5 = new Node<>("5");

        stack1.next = stack2;
        stack2.next = stack3;
        stack3.next = stack4;
//        stack4.next = stack2;
        stack4.next = stack5;

        System.out.println(isCircle(stack1));
        System.out.println(circlePosition(stack1));
        System.out.println(stackMid(stack1));
        System.out.println(findK(stack1, 3));
    }

    private static boolean isCircle(Node<String> stack) {
        if (stack == null) {
            return false;
        }
        Node<String> slow = stack;
        Node<String> fast = stack;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    private static Node<String> circlePosition(Node<String> stack) {
        if (stack == null) {
            return null;
        }
        Node<String> slow = stack;
        Node<String> fast = stack;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow == fast) {
            slow = stack;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }

    private static Node<String> stackMid(Node<String> stack) {
        if (stack == null) {
            return null;
        }
        Node<String> slow = stack;
        Node<String> fast = stack;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node<String> findK(Node<String> stack, int k) {
        if (stack == null) {
            return null;
        }
        Node<String> slow = stack;
        Node<String> fast = stack;
        int count = 0;
        while (count++ < k && fast != null) {
            fast = fast.next;
        }
        if (fast == null) {
            return null;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
