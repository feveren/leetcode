package rent.backtrace;

/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * https://leetcode-cn.com/problems/max-area-of-island/
 */
public class _695_maxAreaOfIsland {
    public static void main(String[] args) {
        _695_maxAreaOfIsland helper = new _695_maxAreaOfIsland();
        System.out.println(helper.maxAreaOfIsland(new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        }));
        System.out.println(helper.maxAreaOfIsland(new int[][] {
                {0,0,0,0,0,0,0,0,0}
        }));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] directs = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int area = dfs(i, j, grid, visited, directs);
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited, int[][] directs) {
        if (!inArea(i, j, grid)) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        int area = 1;
        visited[i][j] = true;
        for (int[] d : directs) {
            area += dfs(i + d[0], j + d[1], grid, visited, directs);
        }
        return area;
    }

    private boolean inArea(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
