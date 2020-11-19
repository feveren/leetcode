package rent.dynamic;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class _042_trap {
    public static void main(String[] args) {
        _042_trap helper = new _042_trap();
        System.out.println(helper.trap2(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(helper.trap2(new int[] {4,2,0,3,2,5}));
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int length = height.length;
        int[] leftMax = new int[length];
        int[] rightMax = new int[length];
        leftMax[0] = height[0];
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
//        System.out.println(Arrays.toString(leftMax));
        rightMax[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
//        System.out.println(Arrays.toString(rightMax));
        int res = 0;
        for (int i = 1; i < length - 1; i++) {
//            int delta = Math.min(leftMax[i], rightMax[i]) - height[i];
//            res += delta;
//            System.out.println(i + " height[i]=" + height[i] + ", delta=" + delta);
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int length = height.length;
        int left = 0;
        int right = length - 1;
        int leftMax = height[0];
        int rightMax = height[length - 1];
        int res = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
