package rent.utils;

import rent.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {
    public static TreeNode buildTree(Integer[] array) {
        return buildTree(array, 0);
    }

    private static TreeNode buildTree(Integer[] array, int parentIndex) {
        if (parentIndex >= array.length) {
            return null;
        }
        Integer value = array[parentIndex];
        if (value == null) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = buildTree(array, parentIndex * 2 + 1);
        node.right = buildTree(array, parentIndex * 2 + 2);
        return node;
    }

    public static TreeNode findNode(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val == value) {
            return root;
        }
        TreeNode left = findNode(root.left, value);
        if (left != null) {
            return left;
        }
        TreeNode right = findNode(root.right, value);
        if (right != null) {
            return right;
        }
        return null;
    }

    public static String toString(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    builder.append(node.val);
                } else {
                    builder.append(node.val + "(");
                    if (node.left != null) {
                        queue.offer(node.left);
                        builder.append(node.left.val);
                    } else {
                        builder.append("_");
                    }
                    builder.append(",");
                    if (node.right != null) {
                        queue.offer(node.right);
                        builder.append(node.right.val);
                    } else {
                        builder.append("_");
                    }
                    builder.append(")");
                }
                builder.append("  ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
