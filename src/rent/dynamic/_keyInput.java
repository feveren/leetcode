package rent.dynamic;

public class _keyInput {
    public static void main(String[] args) {
        System.out.println(input2(3));
        System.out.println(input2(7));
    }

    public static int input(int n) {
        return input(n, 0, 0);
    }

    public static int input(int n, int aNum, int copyNum) {
        if (n <= 0) {
            return aNum;
        }
        int a = input(n - 1, aNum + 1, copyNum);
        int v = input(n - 1, aNum + copyNum, copyNum);
        int ac = input(n - 2, aNum, aNum);
        int res = Math.max(a, Math.max(v, ac));
        return res;
    }

    public static int input2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j < i; j++) {
                // 输入i次时，判断只按A和按A+ACV的最大值
                // j - 2去掉AC的两次操作
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[n];
    }
}
