import org.junit.Test;
import rent.model.TreeNode;

public class BST {
    private TreeNode buildTreeNode() {
        TreeNode node = new TreeNode(2);
        node.left = new TreeNode(1);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(0);
//        node.left.right = new TreeNode(1);
//        node.right.left = new TreeNode(0);
        return node;
    }

    @Test
    public void traverse() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        traverse(node);
    }

    public void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        traverse(node.left);
        traverse(node.right);
    }

    @Test
    public void isSame() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);

        TreeNode node2 = new TreeNode(1);
        node2.left = new TreeNode(2);
        node2.right = new TreeNode(3);
        node2.left.left = new TreeNode(4);

        System.out.println(isSame(node, node2));
    }

    public boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return isSame(node1.left, node2.left) &&
                isSame(node1.right, node2.right);
    }

    @Test
    public void isValidBST() {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.right = new TreeNode(10);
        node.right.left = new TreeNode(8);
        node.right.right = new TreeNode(12);
        node.right.right.left = new TreeNode(9);

        System.out.println(isValidBST(node));
    }

    boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        return isValidBST(root.left, min, root) // 左边节点最大值为当前节点，最小值min
                && isValidBST(root.right, root, max); // 最右边节点最小值为当前节点，最大值max
    }

    @Test
    public void searchBST() {
        TreeNode root = buildTreeNode();
        TreeNode res = searchBST(root, 1);
        System.out.println(res + " " + res.left + " " + res.right);
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        if (root.val < val) {
            return searchBST(root.right, val);
        }
        return null;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 替换为右子树最小值
            root.val = getSuccessorValue(root);
            root.right = deleteNode(root.right, root.val);
            // 替换为左子树最大值
//            root.val = getPredecessorValue(root);
//            root.left = deleteNode(root.left, root.val);
        }
        return root;
    }

    private int getSuccessorValue(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    private int getPredecessorValue(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    @Test
    public void countNodes() {
        TreeNode node = buildTreeNode();
        System.out.println(countNodes(node));
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = 0;
        TreeNode node = root;
        while (node != null) {
            leftCount++;
            node = node.left;
        }
        int rightCount = 0;
        node = root;
        while (node != null) {
            rightCount++;
            node = node.right;
        }
        if (leftCount == rightCount) {
            return (int) Math.pow(2, leftCount) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
