package rent.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退
 *
 * https://leetcode-cn.com/problems/n-queens
 */
public class _051_NQueens {
    public static final String QUEEN = "Q";
    public static final String BLANK = ".";

    public static void main(String[] args) {
        List<List<String>> list = solveNQueens(8);
        System.out.println("total: " + list.size());
        for (List<String> strings : list) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println();
        }
    }

    private static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] colTrace = new int[n];
        solve(0, n, colTrace, result);
        return result;
    }

    private static void solve(int row, int n, int[] colTrace, List<List<String>> result) {
        if (row == n) {
            List<String> list = new ArrayList<>(n);
            for (int value : colTrace) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (value == j) {
                        builder.append(QUEEN);
                    } else {
                        builder.append(BLANK);
                    }
                }
                list.add(builder.toString());
            }
            result.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(row, i, colTrace)) {
                continue;
            }
            colTrace[row] = i;
            solve(row + 1, n, colTrace, result);
            colTrace[row] = 0;
        }
    }

    private static boolean isValid(int row, int col, int[] colTrace) {
        for (int i = 0; i < row; i++) {
            int traceCol = colTrace[i];
            if (col == traceCol || traceCol == row + col - i || traceCol == i - (row - col)) {
                return false;
            }
        }
        return true;
    }

    private static List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> trace = new ArrayList<>();
        solve2(n, trace, result);
        return result;
    }

    private static void solve2(int n, List<String> trace, List<List<String>> result) {
        if (trace.size() == n) {
            result.add(new ArrayList<>(trace));
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!isValid2(i, trace)) {
                continue;
            }
            if (builder.length() > 0) {
                builder.delete(0, builder.length());
            }
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    builder.append(QUEEN);
                } else {
                    builder.append(BLANK);
                }
            }
            trace.add(builder.toString());
            solve2(n, trace, result);
            trace.remove(trace.size() - 1);
        }
    }

    private static boolean isValid2(int col, List<String> trace) {
        int row = trace.size();
        for (int i = 0; i < trace.size(); i++) {
            int index = trace.get(i).indexOf(QUEEN);
            if (index == col || index == row + col - i || index == i - (row - col)) {
                return false;
            }
        }
        return true;
    }
}
