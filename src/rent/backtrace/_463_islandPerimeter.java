package rent.backtrace;

/**
 * 463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * 输出: 16
 *
 * https://leetcode-cn.com/problems/island-perimeter/
 */
public class _463_islandPerimeter {
    public static void main(String[] args) {
        _463_islandPerimeter helper = new _463_islandPerimeter();
        System.out.println(helper.islandPerimeter(new int[][] {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        }));
    }

    public int islandPerimeter(int[][] grid) {
        int[][] directs = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    return dfs(i, j, grid, visited, directs);
                }
            }
        }
        return 0;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited, int[][] directs) {
        if (!inArea(i, j, grid)) {
            return 1;
        }
        if (grid[i][j] == 0) {
            return 1;
        }
        if (visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int res = 0;
        for (int[] d : directs) {
            res += dfs(i + d[0], j + d[1], grid, visited, directs);
        }
        return res;
    }

    private boolean inArea(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
