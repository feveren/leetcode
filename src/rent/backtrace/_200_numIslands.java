package rent.backtrace;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * https://leetcode-cn.com/problems/number-of-islands/
 */
public class _200_numIslands {
    public static void main(String[] args) {
        _200_numIslands helper = new _200_numIslands();
        System.out.println(helper.numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        }));
        System.out.println(helper.numIslands(new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        }));
    }
    
    public int numIslands(char[][] grid) {
        int[][] directs = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid, visited, directs);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] grid, boolean[][] visited, int[][] directs) {
        if (!inArea(i, j, grid)) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        visited[i][j] = true;
        for (int[] d : directs) {
            dfs(i + d[0], j + d[1], grid, visited, directs);
        }
    }
    
    private boolean inArea(int i, int j, char[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
