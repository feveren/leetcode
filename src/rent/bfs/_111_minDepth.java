package rent.bfs;

import rent.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 *     3
 *    / \
 *   9  20
 *     / \
 *    15  7
 * 返回它的最小深度  2.
 * <p>
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class _111_minDepth {
    public static void main(String[] args) {
//        TreeNode head = new TreeNode(3);
//        head.left = new TreeNode(9);
//        head.right = new TreeNode(20);
//        TreeNode headRight = head.right;
//        headRight.left = new TreeNode(15);
//        headRight.right = new TreeNode(7);

//        TreeNode head = new TreeNode(1);
//        head.left = new TreeNode(2);
//        head.right = new TreeNode(3);
//        TreeNode headLeft = head.left;
//        headLeft.left = new TreeNode(4);
//        headLeft.right = new TreeNode(5);

        TreeNode head = new TreeNode(2);
        head.right = new TreeNode(3);
        TreeNode headRight = head.right;
        headRight.right = new TreeNode(4);
        headRight = headRight.right;
        headRight.right = new TreeNode(5);
        headRight = headRight.right;
        headRight.right = new TreeNode(6);

        System.out.println(minDepth(head));
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) {
            return Math.max(left, right) + 1;
        }
        return Math.min(left, right) + 1;
    }

    public static int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
