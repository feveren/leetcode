package rent.linkedlist;

import java.util.*;

/**
 * 138. 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的 深拷贝。
 *
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 */
public class _138_copyRandomList {
    public static void main(String[] args) {
        _138_copyRandomList helper = new _138_copyRandomList();
        Node node = buildStack(new int[]{1, 2, 3, 4, 5});
        buildRandom(node);
        println(node);
        System.out.println("start copyRandomList");
        node = helper.copyRandomList(node);
        println(node);
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
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            Node curr = map.get(node);
            if (node.next != null) {
                curr.next = map.get(node.next);
            }
            if (node.random != null) {
                curr.random = map.get(node.random);
            }
            node = node.next;
        }
        return map.get(head);
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
}
