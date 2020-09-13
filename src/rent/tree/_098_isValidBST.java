package rent.tree;

import rent.model.TreeNode;
import rent.utils.TreeUtils;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class _098_isValidBST {
    public static void main(String[] args) {
        _098_isValidBST helper = new _098_isValidBST();
        TreeNode node = TreeUtils.buildTree(new Integer[]{3,1,4,null,null,2,6});
        System.out.println(helper.isValidBST(node));
        System.out.println(helper.isValidBST2(node));
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }

    private Integer mPrev;

    public boolean isValidBST2(TreeNode root) {
        mPrev = null;
        return inorder(root);
    }

    public boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isValid = inorder(root.left);
        if (!isValid) {
            return false;
        }
        if (mPrev != null && root.val <= mPrev) {
            return false;
        }
        mPrev = root.val;
        isValid = inorder(root.right);
        if (!isValid) {
            return false;
        }
        return true;
    }
}
