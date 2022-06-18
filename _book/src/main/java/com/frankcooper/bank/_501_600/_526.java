package com.frankcooper.platform.leetcode.bank._501_600;

/**
 * https://leetcode-cn.com/problems/beautiful-arrangement/solution/java-zhuang-tai-ya-suo-dp-dai-zhu-shi-yi-dong-by-d/
 */
public class _526 {
    static _526 handler = new _526();

    public static void main(String[] args) {

        handler.countArrangement(4);

    }

    public int countArrangement(int N) {

        int[] dp = new int[1 << N];
        dp[0] = 1;
        for (int state = 0; state < (1 << N); state++) {
            int cnt = 1;
            for (int i = 0; i < N; i++) {
//                System.out.println(BitOpUtils.addZeroForNum(String.valueOf(state >> i & 1), 4));
                cnt += state >> i & 1;
            }
            for (int j = 1; j <= N; j++) {
                if ((state >> (j - 1) & 1) == 0 && (cnt % j == 0 || j % cnt == 0)) {
                    System.out.printf("state:%d,cnt:%d,j:%d\n", state, cnt, j);
                    System.out.printf("t:%d\n", state | (1 << (j - 1)));
                    dp[state | (1 << (j - 1))] += dp[state];
                }
            }
        }
        return dp[(1 << N) - 1];
    }

}
