package rent.tree;

import rent.model.TreeNode;
import rent.utils.ListUtils;
import rent.utils.TreeUtils;

import java.util.*;

/**
 * 314. 二叉树的垂直遍历
 * 题目描述:
 * 给定一个二叉树，返回其结点 垂直方向（从上到下，逐列）遍历的值。
 *
 * 如果两个结点在同一行和列，那么顺序则为 从左到右。
 *
 * 示例:
 * 示例 1：
 *
 * 输入: [3,9,20,null,null,15,7]
 *
 *    3
 *   /\
 *  /  \
 * 9   20
 *     /\
 *    /  \
 *   15   7
 *
 * 输出:
 *
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 *
 * https://leetcode-cn.com/problems/binary-tree-vertical-order-traversal/
 */
public class _314_verticalOrder {
    public static void main(String[] args) {
        _314_verticalOrder helper = new _314_verticalOrder();
        TreeNode node = TreeUtils.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        List<List<Integer>> list = helper.verticalOrder(node);
        System.out.println(ListUtils.toString(list));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[] {0, root});
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Object[] array = queue.poll();
            int index = Integer.parseInt(String.valueOf(array[0]));
            TreeNode node = (TreeNode) array[1];
            List<Integer> list = map.get(index);
            if (list == null) {
                list = new ArrayList<>();
                map.put(index, list);
            }
            list.add(node.val);
            min = Math.min(min, index);
            if (node.left != null) {
                queue.offer(new Object[] {index - 1, node.left});
            }
            if (node.right != null) {
                queue.offer(new Object[] {index + 1, node.right});
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        int length = min + map.size();
        for (int i = min; i < length; i++) {
            result.add(map.get(i));
        }
        return result;
    }
}
