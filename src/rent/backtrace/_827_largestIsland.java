package rent.backtrace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 827. 最大人工岛
 * 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
 * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
 *
 * 示例 1:
 * 输入: [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 *
 * 示例 2:
 * 输入: [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 *
 * 示例 3:
 * 输入: [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *
 * https://leetcode-cn.com/problems/making-a-large-island/
 */
public class _827_largestIsland {
    public static void main(String[] args) {
        _827_largestIsland helper = new _827_largestIsland();
        System.out.println(helper.largestIsland(new int[][] {
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1}
        }));
        System.out.println(helper.largestIsland(new int[][] {
                {0, 1},
                {1, 1}
        }));
    }

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] codes = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        int[][] directs = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        int code = 1;
        int res = 0;
        Map<Integer, Integer> areas = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(i, j, grid, visited, directs, code, codes);
                    areas.put(code, area);
                    res = Math.max(res, area);
                    code++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = link(i, j, grid, directs, codes, areas);
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited, int[][] directs, int code, int[][] codes) {
        if (!inArea(i, j, grid)) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        visited[i][j] = true;
        codes[i][j] = code;
        int res = 1;
        for (int[] d : directs) {
            res += dfs(i + d[0], j + d[1], grid, visited, directs, code, codes);
        }
        return res;
    }

    private int link(int i, int j, int[][] grid, int[][] directs, int[][] codes, Map<Integer, Integer> areas) {
        Set<Integer> set = new HashSet<Integer>();
        int res = 1;
        for (int[] d : directs) {
            int x = i + d[0];
            int y = j + d[1];
            if (inArea(x, y, grid) && grid[x][y] == 1) {
                if (set.contains(codes[x][y])) {
                    continue;
                }
                res += areas.get(codes[x][y]);
                set.add(codes[x][y]);
            }
        }
        return res;
    }

    private boolean inArea(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
