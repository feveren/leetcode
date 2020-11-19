package rent.backtrace;

import rent.utils.ArrayUtils;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * https://leetcode-cn.com/problems/surrounded-regions/
 */
public class _130_solve {
    public static void main(String[] args) {
        _130_solve helper = new _130_solve();
//        char[][] board = new char[][] {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}
//        };
//        helper.solve(board);
//        ArrayUtils.print(board);
        char[][] board = new char[][] {
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}
        };
        helper.solve(board);
        ArrayUtils.print(board);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int[][] directs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, board, directs);
            }
            if (board[i][col - 1] == 'O') {
                dfs(i, col - 1, board, directs);
            }
        }
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                dfs(0, i, board, directs);
            }
            if (board[row - 1][i] == 'O') {
                dfs(row - 1, i, board, directs);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board, int[][] directs) {
        if (!inArea(i, j, board)) {
            return;
        }
        if (board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'B';
        for (int[] d : directs) {
            dfs(i + d[0], j + d[1], board, directs);
        }
    }

    private boolean inArea(int i, int j, char[][] board) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }
}
