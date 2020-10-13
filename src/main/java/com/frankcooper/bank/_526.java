package com.frankcooper.bank;

/**
 * https://leetcode-cn.com/problems/beautiful-arrangement/solution/java-zhuang-tai-ya-suo-dp-dai-zhu-shi-yi-dong-by-d/
 */
public class _526 {
    static _526 handler = new _526();

    public static void main(String[] args) {

    }

    public int countArrangement(int N) {

        int[] dp = new int[1 << N];
        dp[0] = 1;
        for (int state = 0; state < (1 << N); state++) {
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                cnt += state >> i & 1;
            }
            for (int i = 1; i <= N; i++) {
                if ((state >> (i - 1) & 1) == 0 && (cnt % i == 0 || i % cnt == 0)) {
                    dp[state | (1 << (i - 1))] += dp[state];
                }
            }
        }
        return dp[(1 << N) - 1];
    }

}
