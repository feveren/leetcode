import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Backtrack {
    @Test
    public void solveNQueues() {
        int n = 8;
        List<List<String>> res = solveNQueens(n);

        System.out.println(res.size());

        for (List<String> strings : res) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println();
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        int[] trace = new int[n];
        backtrack(res, trace, n, 0);
        return res;
    }

    public void backtrack(List<List<String>> res, int[] trace, int n, int step) {
        if (step == n) {
            res.add(buildQueueStr(trace, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isQueueValid(trace, step, i)) {
                continue;
            }
            trace[step] = i;
            backtrack(res, trace, n, step + 1);
            trace[step] = 0;
        }
    }

    private List<String> buildQueueStr(int[] trace, int n) {
        List<String> list = new ArrayList<>(n);
        for (int i = 0; i < trace.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (trace[i] == j) {
                    builder.append("Q");
                } else {
                    builder.append(".");
                }
            }
            list.add(builder.toString());
        }
        return list;
    }

    private boolean isQueueValid(int[] trace, int row, int col) {
        for (int i = 0; i < row; i++) {
            int queenCol = trace[i];
            if (col == queenCol
                    || col + (i - row) == queenCol
                    || col - (i - row) == queenCol) {
                return false;
            }
        }
        return true;
    }
}
