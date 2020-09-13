package rent.tree;

import rent.model.TreeNode;
import rent.utils.ListUtils;
import rent.utils.TreeUtils;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class _103_zigzagLevelOrder {
    public static void main(String[] args) {
        _103_zigzagLevelOrder helper = new _103_zigzagLevelOrder();
        TreeNode node = TreeUtils.buildTree(new Integer[]{1,2,3,4,null,null,5});
        List<List<Integer>> list = helper.zigzagLevelOrder(node);
        System.out.println(ListUtils.toString(list));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (reverse) {
                Collections.reverse(list);
            }
            result.add(list);
            reverse = !reverse;
        }
        return result;
    }
}
