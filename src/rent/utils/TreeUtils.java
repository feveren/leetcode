package rent.utils;

import rent.model.TreeNode;

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
}
