package tree;

import org.junit.Test;
import rent.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Traversal {
    private TreeNode create() {
        //       1
        //     2   3
        //    4 5 6 7
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        left = new TreeNode(4);
        right = new TreeNode(5);
        root.left.left = left;
        root.left.right = right;

        left = new TreeNode(6);
        right = new TreeNode(7);
        root.right.left = left;
        root.right.right = right;
        return root;
    }

    @Test
    public void preorderTest() {
        TreeNode node = create();
        preorder(node);
    }

    private void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    @Test
    public void inorderTest() {
        TreeNode node = create();
        inorderLoop(node);
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    private void inorderLoop(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.val);
                node = node.right;
            }
        }
    }

    @Test
    public void postorderTest() {
        TreeNode node = create();
        postorderLoop(node);
    }

    private void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.val);
    }

    private void postorderLoop(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode lastVisit = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (node.right == null || node.right == lastVisit) {
                    System.out.println(node.val);
                    lastVisit = node;
                    node = null;
                } else {
                    stack.push(node);
                    node = node.right;
                }
            }
        }
    }

    @Test
    public void levelOrderTest() {
        TreeNode node = create();
        levelOrder(node);
    }

    private void levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            System.out.print(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        System.out.println();
    }

    @Test
    public void dfsTraversal() {
        TreeNode node = create();
        List<TreeNode> list = divide(node);
        for (TreeNode treeNode : list) {
            System.out.println(treeNode.val);
        }
    }

    private List<TreeNode> divide(TreeNode node) {
        if (node == null) {
            return null;
        }
        List<TreeNode> left = divide(node.left);
        List<TreeNode> right = divide(node.right);
        List<TreeNode> result = new ArrayList<>();
        result.add(node);
        if (left != null) {
            result.addAll(left);
        }
        if (right != null) {
            result.addAll(right);
        }
        return result;
    }
}
