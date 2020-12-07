package rent.array;

import rent.model.TreeNode;
import rent.utils.TreeUtils;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一
 * 个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例:
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class _297_Codec {
    public static void main(String[] args) {
        _297_Codec.Codec codec = new _297_Codec.Codec();
        TreeNode treeNode = TreeUtils.buildTree(new Integer[]{1, 2, 3, null, null, 4, 5, null, null, null, null, 6, 7});
        System.out.println(codec.serialize(treeNode));
        System.out.println(TreeUtils.toString(codec.deserialize("1,2,null,null,3,4,6,null,null,7,null,null,5,null,null")));
    }

    public static class Codec {
        private static final String NULL = "null";
        private static final String SEPARATOR = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            serialize(root, builder);
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }

        private void serialize(TreeNode node, StringBuilder builder) {
            if (node == null) {
                builder.append(NULL).append(SEPARATOR);
                return;
            }
            builder.append(node.val).append(SEPARATOR);
            serialize(node.left, builder);
            serialize(node.right, builder);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || "".equals(data.trim())) {
                return null;
            }
            String[] array = data.split(SEPARATOR);
            if (array.length == 0) {
                return null;
            }
            return deserialize(array, new int[] {0});
        }

        private TreeNode deserialize(String[] array, int[] index) {
            int i = index[0];
            if (NULL.equals(array[i])) {
                index[0]++;
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(array[i]));
            index[0]++;
            node.left = deserialize(array, index);
            node.right = deserialize(array, index);
            return node;
        }
    }
}
