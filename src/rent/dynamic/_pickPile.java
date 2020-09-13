package rent.dynamic;

public class _pickPile {
    public static void main(String[] args) {
        System.out.println(pick(new int[] {3, 9, 1, 2}));
    }

    public static int pick(int[] piles) {
        int len = piles.length;
        int[][][] dp = new int[len][len][2];
        for (int i = 0; i < len; i++) {
            dp[i][i][0] = piles[i];
        }
        // 从底向上遍历
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                int left = dp[i + 1][j][1] + piles[i];
                int right = dp[i][j - 1][1] + piles[j];
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }
        // 歇着遍历
//        for (int l = 2; l <= len; l++) {
//            for (int i = 0; i <= len - l; i++) {
//                int j = l + i - 1;
//                int left = dp[i + 1][j][1] + piles[i];
//                int right = dp[i][j - 1][1] + piles[j];
//                if (left > right) {
//                    dp[i][j][0] = left;
//                    dp[i][j][1] = dp[i + 1][j][0];
//                } else {
//                    dp[i][j][0] = right;
//                    dp[i][j][1] = dp[i][j - 1][0];
//                }
//            }
//        }
        int[] res = dp[0][len - 1];
        return res[0] - res[1];
    }
}
