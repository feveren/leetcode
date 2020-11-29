package rent.math;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *
 * https://leetcode-cn.com/problems/count-primes/
 */
public class _204_countPrimes {
    public static void main(String[] args) {
        _204_countPrimes helper = new _204_countPrimes();
        System.out.println(helper.countPrimes(499979));
    }

    public int countPrimes(int n) {
        boolean[] notPrim = new boolean[n];
        for (int i = 2; i * i < n; i++) {
            if (notPrim[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                notPrim[j] = true;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrim[i]) {
                count++;
            }
        }
        return count;
    }
}
