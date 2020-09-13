package rent.tree;

import rent.model.TreeNode;
import rent.utils.TreeUtils;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1：
 * 输入：[1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出：6
 *
 * 示例 2：
 * 输入：[-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出：42
 *
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
public class _124_maxPathSum {

    public static void main(String[] args) {
        _124_maxPathSum instance = new _124_maxPathSum();
        TreeNode node = TreeUtils.buildTree(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println(instance.maxPathSum(node));

        node = TreeUtils.buildTree(new Integer[]{-10, 2, 3});
        System.out.println(instance.maxPathSum(node));
    }

    private int mMaxValue;

    public int maxPathSum(TreeNode root) {
        mMaxValue = Integer.MIN_VALUE;
        calcMaxPathSum(root);
        return mMaxValue;
    }

    private int calcMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(calcMaxPathSum(root.left), 0);
        int right = Math.max(calcMaxPathSum(root.right), 0);
        mMaxValue = Math.max(mMaxValue, left + root.val + right);
        return root.val + Math.max(left, right);
    }
}
