package rent.array;

import rent.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class _037_solveSudoku {
    public static void main(String[] args) {
        char[][] board = new char[9][9];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }
        solveSudoku(board);
        ArrayUtils.print(board);
    }

    public static void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    private static boolean backtrack(char[][] board, int row, int col) {
        if (col >= 9) {
            return backtrack(board, row + 1, 0);
        }
        if (row >= 9) {
            return true;
        }
        if (board[row][col] != '.') {
            return backtrack(board, row, col + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, row, col, ch)) {
                continue;
            }
            board[row][col] = ch;
            if (backtrack(board, row, col + 1)) {
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }

    private static boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
            if (board[i][col] == num) {
                return false;
            }
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}
